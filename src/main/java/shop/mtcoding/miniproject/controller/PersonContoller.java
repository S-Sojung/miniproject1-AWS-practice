package shop.mtcoding.miniproject.controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.StringTokenizer;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import shop.mtcoding.miniproject.dto.ResponseDto;
import shop.mtcoding.miniproject.dto.Resume.ResumeReq.ResumeUpdateReqDto;
import shop.mtcoding.miniproject.dto.person.PersonReq.JoinPersonReqDto;
import shop.mtcoding.miniproject.dto.person.PersonReq.LoginPersonReqDto;
import shop.mtcoding.miniproject.dto.person.PersonReqDto.PersonUpdateDto;
import shop.mtcoding.miniproject.dto.personProposal.PersonProposalResp.PersonProposalListRespDto;
import shop.mtcoding.miniproject.dto.personProposal.PersonProposalResp.PersonProposalStringListRespDto;
import shop.mtcoding.miniproject.dto.personScrap.PersonScrapResDto.PersonScrapIntegerResDto;
import shop.mtcoding.miniproject.dto.personScrap.PersonScrapResDto.PersonScrapTimeStampResDto;
import shop.mtcoding.miniproject.dto.post.PostResp.PostDtailResDto;
import shop.mtcoding.miniproject.dto.post.PostResp.PostMainRespDto;
import shop.mtcoding.miniproject.dto.post.PostResp.PostMainWithScrapRespDto;
import shop.mtcoding.miniproject.dto.post.PostResp.PostRecommendIntegerRespDto;
import shop.mtcoding.miniproject.dto.post.PostResp.PostRecommendTimeStampResDto;
import shop.mtcoding.miniproject.handler.ex.CustomApiException;
import shop.mtcoding.miniproject.handler.ex.CustomException;
import shop.mtcoding.miniproject.model.Company;
import shop.mtcoding.miniproject.model.CompanyRepository;
import shop.mtcoding.miniproject.model.Person;
import shop.mtcoding.miniproject.model.PersonProposalRepository;
import shop.mtcoding.miniproject.model.PersonRepository;
import shop.mtcoding.miniproject.model.PersonScrap;
import shop.mtcoding.miniproject.model.PersonScrapRepository;
import shop.mtcoding.miniproject.model.Post;
import shop.mtcoding.miniproject.model.PostRepository;
import shop.mtcoding.miniproject.model.ProposalPass;
import shop.mtcoding.miniproject.model.ProposalPassRepository;
import shop.mtcoding.miniproject.model.Resume;
import shop.mtcoding.miniproject.model.ResumeRepository;
import shop.mtcoding.miniproject.model.Skill;
import shop.mtcoding.miniproject.model.SkillFilter;
import shop.mtcoding.miniproject.model.SkillFilterRepository;
import shop.mtcoding.miniproject.model.SkillRepository;
import shop.mtcoding.miniproject.model.User;
import shop.mtcoding.miniproject.model.UserRepository;
import shop.mtcoding.miniproject.service.PersonProposalService;
import shop.mtcoding.miniproject.service.PersonService;
import shop.mtcoding.miniproject.service.ResumeService;
import shop.mtcoding.miniproject.util.CvTimestamp;
import shop.mtcoding.miniproject.util.EncryptionUtils;

@Controller
public class PersonContoller {

    @Autowired
    private RedisTemplate<String, User> redisTemplate;

    @Autowired
    private HttpSession session;

    @Autowired
    private ResumeService resumeService;

    @Autowired
    private ResumeRepository resumeRepository;

    @Autowired
    private PersonService personService;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PersonProposalRepository personProposalRepository;
    @Autowired
    private ProposalPassRepository proposalPassRepository;
    @Autowired
    private PersonProposalService personProposalService;

    @Autowired
    private SkillFilterRepository skillFilterRepository;

    @Autowired
    private PersonScrapRepository personScrapRepository;

    // public void personMocLogin() {
    // User user = new User();
    // user.setId(1);
    // user.setCInfoId(0);
    // user.setPInfoId(1);
    // user.setEmail("ssar@nate.com");
    // user.setPassword("1234");
    // session.setAttribute("principal", user);
    // }

    @GetMapping("/personLoginForm")
    public String personLoginForm() {
        return "person/loginForm";
    }

    @PostMapping("/personLogin")
    public String personLogin(LoginPersonReqDto loginPersonReqDto) {
        if (loginPersonReqDto.getEmail() == null ||
                loginPersonReqDto.getEmail().isEmpty()) {
            throw new CustomException("이메일을 작성해주세요");
        }
        if (loginPersonReqDto.getPassword() == null ||
                loginPersonReqDto.getPassword().isEmpty()) {
            throw new CustomException("패스워드를 작성해주세요");
        }
        // DB Salt 값
        User userCheck = userRepository.findByEmail(loginPersonReqDto.getEmail());
        if (userCheck == null) {
            throw new CustomException("이메일 혹은 패스워드가 잘못입력되었습니다.");
        }
        String salt = userCheck.getSalt();
        // DB Salt + 입력된 password 해싱
        loginPersonReqDto.setPassword(EncryptionUtils.encrypt(loginPersonReqDto.getPassword(), salt));

        User principal = userRepository.findPersonByEmailAndPassword(loginPersonReqDto.getEmail(),
                loginPersonReqDto.getPassword());
        if (principal == null) {
            throw new CustomException("이메일 혹은 패스워드가 잘못입력되었습니다.");
        }

        redisTemplate.opsForValue().set("principal", principal);
        session.setAttribute("principal", principal);
        return "redirect:/person/main";
    }

    @GetMapping("/personJoinForm1")
    public String personJoinForm1() {
        return "person/joinForm1";
    }

    @PostMapping("/personJoin")
    public String join(JoinPersonReqDto joinPersonReqDto, RedirectAttributes redirectAttributes) {

        if (joinPersonReqDto.getName() == null ||
                joinPersonReqDto.getName().isEmpty()) {
            throw new CustomException("이름을 작성해주세요");
        }
        if (joinPersonReqDto.getPassword() == null ||
                joinPersonReqDto.getPassword().isEmpty()) {
            throw new CustomException("비밀번호를 작성해주세요");
        }
        if (joinPersonReqDto.getEmail() == null ||
                joinPersonReqDto.getEmail().isEmpty()) {
            throw new CustomException("이메일을 작성해주세요");
        }
        int id = personService.join(joinPersonReqDto);

        redirectAttributes.addAttribute("pInfoId", id);
        // Person 인서트를 이름만!
        // Person 인서트한 id 값을 유저에게 인서트하기

        return "redirect:/personJoinForm2";
    }

    @GetMapping("/personJoinForm2")
    public String personJoinForm2(Model model, int pInfoId) {
        model.addAttribute("pInfoId", pInfoId);
        model.addAttribute("skills", Skill.madeSkills());
        return "person/joinForm2";
    }

    @PostMapping("/personJoin2")
    public String join(String[] skills, Integer pInfoId) {

        String checkedSkills = "";
        for (int i = 0; i < skills.length; i++) {
            if (checkedSkills != "") {
                checkedSkills += ",";
            }
            checkedSkills += skills[i];
            // System.out.println(checkedSkills); 테스트

        }
        personService.join2(checkedSkills, pInfoId);

        return "redirect:/personLoginForm";
    }

    @GetMapping({ "/person/main", "/person" })
    public String personMain(Model model) {
        User principal = (User) session.getAttribute("principal");

        // 회사로고, 회사이름, 공고이름, 회사 주소, D-day
        // cInfo : 회사로고, 회사이름, 회사주소
        // 공고 정보 : 공고이름, 디데이
        List<PostMainRespDto> postList = (List<PostMainRespDto>) postRepository.findAllWithCInfo();
        List<PostMainWithScrapRespDto> postList2 = new ArrayList<>();
        for (PostMainRespDto p : postList) {
            PostMainWithScrapRespDto psDto = new PostMainWithScrapRespDto();

            try {
                PersonScrap ps = personScrapRepository.findByPInfoIdAndPostId(principal.getPInfoId(), p.getPostId());
                if (ps == null) {
                    psDto.setScrap(0);
                } else {
                    psDto.setScrap(1);
                }
                psDto.setAddress(p.getAddress());
                psDto.setCInfoId(p.getCInfoId());
                psDto.setDeadline(p.getDeadline());
                psDto.setLogo(p.getLogo());
                psDto.setName(p.getName());
                psDto.setPostId(p.getPostId());
                psDto.setTitle(p.getTitle());
                postList2.add(psDto);
            } catch (Exception e) {

            }
        }

        model.addAttribute("mainPosts", postList2);
        model.addAttribute("size", postList2.size());

        return "person/main";
    }

    @GetMapping("/person/detail/{id}")
    public String personDetail(@PathVariable int id, Model model) {
        User userPS = (User) session.getAttribute("principal");
        if (userPS == null) {
            throw new CustomException("인증이 되지 않았습니다.", HttpStatus.FORBIDDEN);
        }

        Post postPS = (Post) postRepository.findById(id);
        if (postPS == null) {
            throw new CustomException("없는 공고 입니다.");
        }

        PersonScrap scrap = personScrapRepository.findByPInfoIdAndPostId(userPS.getPInfoId(), postPS.getId());

        PostDtailResDto postPS2 = new PostDtailResDto();
        postPS2.setId(postPS.getId());
        postPS2.setCInfoId(postPS.getCInfoId());
        postPS2.setCIntro(postPS.getCondition());
        postPS2.setCareer(postPS.getCareer());
        postPS2.setCondition(postPS.getCondition());
        postPS2.setEndHour(postPS.getEndHour());
        postPS2.setJobIntro(postPS.getJobIntro());
        postPS2.setPay(postPS.getPay());
        postPS2.setStartHour(postPS.getStartHour());
        postPS2.setTitle(postPS.getTitle());

        if (scrap == null) {
            postPS2.setScrap(0);
        } else {
            postPS2.setScrap(1);
        }

        new Date();

        Company companyPS = (Company) companyRepository.findById(postPS.getCInfoId());
        Skill skillPS = (Skill) skillRepository.findByPostId(id);
        StringTokenizer skills = new StringTokenizer(skillPS.getSkills(), ",");
        Date date = new Date(postPS.getDeadline().getTime());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDeadline = sdf.format(date);

        model.addAttribute("post", postPS2);
        model.addAttribute("company", companyPS);
        model.addAttribute("deadline", formattedDeadline);
        model.addAttribute("skills", skills);
       
        // List<Resume> resumeAll = resumeRepository.findAll();
        // model.addAttribute("resume", resumeAll);

        // 이력서 리스트 불러오기
        List<Resume> resumeList = (List<Resume>) resumeRepository.findAllByPInfoId(userPS.getPInfoId());
        model.addAttribute("resume", resumeList);

        // 이력서 id를 가지고 person_proposal_tb 를 insert 해주기

        return "person/detail";
    }

    @PostMapping("/person/detail/{id}/resume")
    public String submitResume(@PathVariable("id") int id, int selectedResume) {

        User principal = (User) session.getAttribute("principal");
        if (principal == null) {
            throw new CustomException("인증이 되지 않았습니다", HttpStatus.UNAUTHORIZED);
        }

        int pInfoId = principal.getPInfoId();

        // post아이디는 여기 id! + resumeid는 int selectedResume
        personProposalService.지원하기(pInfoId, id, selectedResume, 0); // status 합불합격상태(0은 대기중)

        return "redirect:/person/history";
    }

    @GetMapping("/person/recommend")
    public String personRecommend(Model model) {
        User principal = (User) session.getAttribute("principal");
        if (principal == null) {
            throw new CustomException("인증이 되지 않았습니다.", HttpStatus.FORBIDDEN);
        }
        // person skill 찾기
        Skill principalSkills = skillRepository.findByPInfoId(principal.getPInfoId());
        String[] principalSKillArr = principalSkills.getSkills().split(",");
        List<SkillFilter> principalSkilFilters = new ArrayList<>();
        for (String principalSkill : principalSKillArr) {
            List<SkillFilter> s = skillFilterRepository.findSkillNameForPerson(principalSkill);
            principalSkilFilters.addAll(s);

        }

        // key : count 중복 포함하지 않고 map 저장
        HashMap<Integer, Integer> postAndCount = new HashMap<>();
        for (SkillFilter psf : principalSkilFilters) {
            postAndCount.put(psf.getPostId(), postAndCount.getOrDefault(psf.getPostId(), 0) + 1);
        }
        Set<Integer> key = postAndCount.keySet();

        HashMap<Integer, Integer> postAndCount2 = new HashMap<>();
        for (Integer k : key) {
            Integer count = postAndCount.getOrDefault(k, 0);
            // System.out.println("테스트: " + k + "-" + count);
            if (count >= 2) {
                postAndCount2.put(k, count);
            }
        }

        // 내림차순 정렬
        List<Entry<Integer, Integer>> postIdList = new ArrayList<>(postAndCount2.entrySet());
        Collections.sort(postIdList, new Comparator<Entry<Integer, Integer>>() {
            public int compare(Entry<Integer, Integer> c1, Entry<Integer, Integer> c2) {
                return c2.getValue().compareTo(c1.getValue());
            }
        });

        List<PostRecommendIntegerRespDto> postList = new ArrayList<>();
        for (Entry<Integer, Integer> entry : postIdList) {
            try {
                // System.out.println("테스트: 1");
                PostRecommendTimeStampResDto p = postRepository.findByPostIdToRecmmend(entry.getKey());
                if (p == null) {
                    continue;
                }
                PostRecommendIntegerRespDto p2 = new PostRecommendIntegerRespDto();
                p2.setAddress(p.getAddress());
                p2.setLogo(p.getLogo());
                p2.setName(p.getName());
                p2.setPostId(p.getPostId());
                p2.setTitle(p.getTitle());
                p2.setDeadline(CvTimestamp.ChangeDDay(p.getDeadline()));
                // System.out.println("테스트: 2");

                PersonScrap ps = personScrapRepository.findByPInfoIdAndPostId(principal.getPInfoId(), p2.getPostId());
                // System.out.println("테스트: 3");

                if (ps == null) {
                    p2.setScrap(0);
                } else {
                    p2.setScrap(1);
                }
                postList.add(p2);
            } catch (Exception e) {
                throw new CustomException("실패");
            }
        }
        model.addAttribute("postList", postList);
        return "person/recommend";
    }

    @GetMapping("/person/info")
    public String personInfo(Model model) {
        User principal = (User) session.getAttribute("principal");

        Person PersonPS = personRepository.findById(principal.getPInfoId());

        model.addAttribute("person", PersonPS);
        Skill pSkill = skillRepository.findByPInfoId(principal.getPInfoId());
        // null point exception
        String pSkills = pSkill.getSkills();
        String[] pSkillArr = pSkills.split(",");

        model.addAttribute("pSkillArr", pSkillArr);

        return "person/info";
    }

    @GetMapping("/person/updateInfoForm")
    public String personUpdateInfoForm(Model model) {

        User principal = (User) session.getAttribute("principal");
        Person PersonPS = personRepository.findById(principal.getPInfoId());

        model.addAttribute("person", PersonPS);

        Skill pSkill = skillRepository.findByPInfoId(principal.getPInfoId());
        String pSkills = pSkill.getSkills();

        model.addAttribute("pSkills", pSkills);
        model.addAttribute("skills", Skill.madeSkills());
        return "person/updateInfoForm";
    }

    @PutMapping("/person/updateInfo")
    public ResponseEntity<?> personUpdate(@RequestBody PersonUpdateDto personUpdateDto) {
        // 필수인지 헷갈림
        User principal = (User) session.getAttribute("principal");
        Person PersonPS = personRepository.findById(principal.getPInfoId());

        if (PersonPS == null) {
            throw new CustomApiException("존재하지 않는 유저입니다");
        }
        //
        if (personUpdateDto.getName() == null || personUpdateDto.getName().isEmpty()) {
            throw new CustomApiException("이름을 확인해주세요");
        }
        if (personUpdateDto.getBirthday() == null) {
            throw new CustomApiException("birthday 확인해주세요");
        }
        if (personUpdateDto.getPhone() == null || personUpdateDto.getPhone().isEmpty()) {
            throw new CustomApiException("전화 번호를 확인해주세요");
        }
        if (personUpdateDto.getEmail() == null || personUpdateDto.getEmail().isEmpty()) {
            throw new CustomApiException("이메일을 확인해주세요");
        }
        if (personUpdateDto.getAddress() == null || personUpdateDto.getAddress().isEmpty()) {
            throw new CustomApiException("주소를 확인해주세요");
        }

        String pw = EncryptionUtils.encrypt(personUpdateDto.getOriginPassword(), principal.getSalt());

        if (!pw.equals(principal.getPassword())) {
            throw new CustomApiException("비밀번호가 일치하지 않습니다!");
        }

        personService.update(personUpdateDto, principal.getPInfoId());
        User principalPS = (User) userRepository.findById(principal.getId());

        redisTemplate.opsForValue().set("principal", principalPS);
        session.setAttribute("principal", principalPS);
        return new ResponseEntity<>(new ResponseDto<>(1, "회원 정보 수정 완료", null), HttpStatus.OK);
    }

    @GetMapping("/person/history")
    public String personHistory(Model model) {
        User principalPS = (User) session.getAttribute("principal");
        List<PersonProposalListRespDto> personProposalList = personProposalRepository
                .findAllWithPostAndCInfoByPInfoId(principalPS.getPInfoId());

        List<ProposalPass> proposalPassList = proposalPassRepository.findAllByPInfoId(principalPS.getPInfoId());
        if (proposalPassList.size() > 0) {
            model.addAttribute("proposalPassList", proposalPassList);
        }
        List<PersonProposalStringListRespDto> personProposalList2 = new ArrayList<>();

        for (PersonProposalListRespDto pp : personProposalList) {
            Timestamp deadline = pp.getDeadline();
            Date date = new Date(deadline.getTime());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String formattedDeadline = sdf.format(date);
            PersonProposalStringListRespDto dto = new PersonProposalStringListRespDto();

            dto.setId(pp.getId());
            dto.setCreatedAt(pp.getCreatedAt());
            dto.setDeadline(formattedDeadline);
            dto.setName(pp.getName());
            dto.setPInfoId(pp.getPInfoId());
            dto.setPostId(pp.getPInfoId());
            dto.setResumeId(pp.getResumeId());
            dto.setStatus(pp.getStatus());
            dto.setTitle(pp.getTitle());
            personProposalList2.add(dto);
        }
        model.addAttribute("personProposalList", personProposalList2);
        return "person/history";
    }

    @GetMapping("/person/resumes")
    public String personResumes(Model model) {
        User principal = (User) session.getAttribute("principal");
        if (principal == null) {
            throw new CustomException("인증이 되지 않았습니다", HttpStatus.UNAUTHORIZED);
        }
        int pInfoId = principal.getPInfoId();
        List<Resume> resumeAll = resumeRepository.findAllByPInfoId(pInfoId);
        model.addAttribute("resumes", resumeAll);
        model.addAttribute("count", resumeAll.size());
        Person personPS = personRepository.findById(pInfoId);
        model.addAttribute("personPS", personPS);
        return "person/resumes";
    }

    @DeleteMapping("/person/resumes/{id}")
    public ResponseEntity<?> deleteResume(@PathVariable int id) {
        User principal = (User) session.getAttribute("principal");
        if (principal == null) {
            throw new CustomApiException("인증이 되지 않았습니다", HttpStatus.UNAUTHORIZED);
        }
        resumeService.delete(id);
        return new ResponseEntity<>(new ResponseDto<>(1, "게시글 삭제 성공", null), HttpStatus.OK);
    }

    @GetMapping("/person/saveResumeForm")
    public String personSaveResumeForm(Model model) {
        User principal = (User) session.getAttribute("principal");
        if (principal == null) {
            throw new CustomException("인증이 되지 않았습니다", HttpStatus.UNAUTHORIZED);
        }
        int pInfoId = principal.getPInfoId();
        Person personPS = personRepository.findById(pInfoId);
        model.addAttribute("personPS", personPS);
        return "person/saveResumeForm";
    }

    @GetMapping("/person/resumeDetail/{id}")
    public String personResumeDetail(@PathVariable int id, Model model) {

        User principal = (User) session.getAttribute("principal");
        if (principal == null) {
            throw new CustomException("인증이 되지 않았습니다", HttpStatus.UNAUTHORIZED);
        }
        Resume resumePS = resumeRepository.findById(id);
        if (resumePS == null) {
            throw new CustomException("없는 이력서를 수정할 수 없습니다");
        }
        Person personPS = personRepository.findById(resumePS.getPInfoId());

        Skill skillPS = skillRepository.findByResumeId(resumePS.getId());
        
        Date date = new Date(personPS.getBirthday().getTime());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String formattedBirthday = sdf.format(date);
        model.addAttribute("resumeDetail", resumePS);
        model.addAttribute("personDetail", personPS);
        model.addAttribute("birthday", formattedBirthday);
        model.addAttribute("skillDetail", skillPS.getSkills().split(","));
        return "person/resumeDetail";
    }

    @GetMapping("/person/scrap")
    public String personScrap(Model model) {
        User principal = (User) session.getAttribute("principal");
        List<PersonScrapTimeStampResDto> pScrapList = personScrapRepository.findByPInfoId(principal.getPInfoId());

        List<PersonScrapIntegerResDto> pScrapList2 = new ArrayList<>();
        for (PersonScrapTimeStampResDto p : pScrapList) {
            Integer deadline = CvTimestamp.ChangeDDay(p.getDeadline());
            PersonScrapIntegerResDto ps = new PersonScrapIntegerResDto();
            ps.setId(p.getId());
            ps.setPInfoId(p.getPInfoId());
            ps.setPostId(p.getPostId());
            ps.setAddress(p.getAddress());
            ps.setDeadline(deadline);
            ps.setLogo(p.getLogo());
            ps.setName(p.getName());
            ps.setTitle(p.getTitle());
            pScrapList2.add(ps);
        }

        model.addAttribute("pScrapList", pScrapList2);
        model.addAttribute("count", pScrapList.size());
        return "person/scrap";
    }

    @PostMapping("/person/resumes")
    public String personInsertResumeForm(ResumeUpdateReqDto resumeUpdateReqDto, Model model) {
        User principal = (User) session.getAttribute("principal");
        if (principal == null) {
            throw new CustomException("인증이 되지 않았습니다", HttpStatus.UNAUTHORIZED);
        }
        int pInfoId = principal.getPInfoId();
        if (resumeUpdateReqDto.getProfile().isEmpty()) {
            throw new CustomException("프로필 사진을 업로드 해주세요");
        }
        if (resumeUpdateReqDto.getTitle() == null || resumeUpdateReqDto.getTitle().isEmpty()) {
            throw new CustomException("제목를 작성해주세요");
        }
        if (resumeUpdateReqDto.getPortfolio() == null || resumeUpdateReqDto.getPortfolio().isEmpty()) {
            throw new CustomException("포트폴리오 주소를 작성해주세요");
        }
        if (resumeUpdateReqDto.getSelfIntro() == null || resumeUpdateReqDto.getSelfIntro().isEmpty()) {
            throw new CustomException("자기소개서를 작성해주세요");
        }
        if (resumeUpdateReqDto.getName() == null || resumeUpdateReqDto.getName().isEmpty()) {
            throw new CustomException("이름를 작성해주세요");
        }
        if (resumeUpdateReqDto.getPhone() == null || resumeUpdateReqDto.getPhone().isEmpty()) {
            throw new CustomException("휴대폰 번호를 작성해주세요");
        }
        if (resumeUpdateReqDto.getBirthday() == null || resumeUpdateReqDto.getBirthday().isEmpty()) {
            throw new CustomException("생년월일을 작성해주세요");
        }
        if (resumeUpdateReqDto.getSkills() == null || resumeUpdateReqDto.getSkills().isEmpty()) {
            throw new CustomException("기술스택을 선택해주세요");
        }
        resumeService.insertNewResume(pInfoId, resumeUpdateReqDto);
        return "redirect:/person/resumes";
    }

    @GetMapping("/person/updateResume/{id}")
    public String getUpdateResumeForm(@PathVariable int id, Model model) {
        // personMocLogin();
        User principal = (User) session.getAttribute("principal");
        if (principal == null) {
            throw new CustomException("인증이 되지 않았습니다", HttpStatus.UNAUTHORIZED);
        }
        Resume resumePS = resumeRepository.findById(id);
        Person personPS = personRepository.findById(resumePS.getPInfoId());
        Skill skillPS = skillRepository.findByPInfoId(resumePS.getPInfoId());
        model.addAttribute("resumePS", resumePS);
        model.addAttribute("personPS", personPS);
        model.addAttribute("skillPS", skillPS.getSkills());
        model.addAttribute("skills", Skill.madeSkills());
        return "person/updateResumeForm";
    }

    @PostMapping("/person/updateResume/{id}")
    public String UpdateResumeForm(@PathVariable int id, ResumeUpdateReqDto resumeUpdateReqDto, Model model) {
        User principal = (User) session.getAttribute("principal");
        if (principal == null) {
            throw new CustomException("인증이 되지 않았습니다", HttpStatus.UNAUTHORIZED);
        }
        if (resumeUpdateReqDto.getProfile().isEmpty()) {
            throw new CustomException("프로필 사진을 업로드 해주세요");
        }
        if (resumeUpdateReqDto.getTitle() == null || resumeUpdateReqDto.getTitle().isEmpty()) {
            throw new CustomException("제목를 작성해주세요");
        }
        if (resumeUpdateReqDto.getPortfolio() == null || resumeUpdateReqDto.getPortfolio().isEmpty()) {
            throw new CustomException("포트폴리오 주소를 작성해주세요");
        }
        if (resumeUpdateReqDto.getSelfIntro() == null || resumeUpdateReqDto.getSelfIntro().isEmpty()) {
            throw new CustomException("자기소개서를 작성해주세요");
        }
        if (resumeUpdateReqDto.getName() == null || resumeUpdateReqDto.getName().isEmpty()) {
            throw new CustomException("이름를 작성해주세요");
        }
        if (resumeUpdateReqDto.getPhone() == null || resumeUpdateReqDto.getPhone().isEmpty()) {
            throw new CustomException("휴대폰 번호를 작성해주세요");
        }
        if (resumeUpdateReqDto.getBirthday() == null || resumeUpdateReqDto.getBirthday().isEmpty()) {
            throw new CustomException("생년월일을 작성해주세요");
        }
        if (resumeUpdateReqDto.getSkills() == null || resumeUpdateReqDto.getSkills().isEmpty()) {
            throw new CustomException("기술스택을 선택해주세요");
        }
        int pInfoId = principal.getPInfoId();
        resumeService.updateById(id, pInfoId, resumeUpdateReqDto);
        Resume resumePS = resumeRepository.findById(id);
        model.addAttribute("resumePS", resumePS);

        return "redirect:/person/resumes";

    }

}
