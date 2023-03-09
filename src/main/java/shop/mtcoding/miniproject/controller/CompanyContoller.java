package shop.mtcoding.miniproject.controller;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import shop.mtcoding.miniproject.dto.ResponseDto;
import shop.mtcoding.miniproject.dto.Resume.ResumeRes.ResumeRecommendArrDto;
import shop.mtcoding.miniproject.dto.Resume.ResumeRes.ResumeRecommendDto;
import shop.mtcoding.miniproject.dto.Resume.ResumeRes.ResumeWithPostInfoRecommendDto;
import shop.mtcoding.miniproject.dto.company.CompanyReq.JoinCompanyReqDto;
import shop.mtcoding.miniproject.dto.company.CompanyReq.LoginCompanyReqDto;
import shop.mtcoding.miniproject.dto.company.CompanyReqDto.CompanyUpdateInfoDto;
import shop.mtcoding.miniproject.dto.companyScrap.CompanyScrapResDto.CompanyScrapWithResumeInfoResArrDto;
import shop.mtcoding.miniproject.dto.companyScrap.CompanyScrapResDto.CompanyScrapWithResumeInfoResDto;
import shop.mtcoding.miniproject.dto.personProposal.PersonProposalReq.CompanyProposalStatusReqDto;
import shop.mtcoding.miniproject.dto.personProposal.PersonProposalResp.CompanyProposalListDateRespDto;
import shop.mtcoding.miniproject.dto.personProposal.PersonProposalResp.CompanyProposalListRespDto;
import shop.mtcoding.miniproject.dto.personProposal.PersonProposalResp.PersonProposalDetailRespDto;
import shop.mtcoding.miniproject.dto.post.PostReq.PostSaveReqDto;
import shop.mtcoding.miniproject.dto.post.PostReq.PostUpdateReqDto;
import shop.mtcoding.miniproject.dto.post.PostResp.PostTitleRespDto;
import shop.mtcoding.miniproject.dto.post.PostResp.postIdAndSkillsDto;
import shop.mtcoding.miniproject.dto.proposalPass.ProposalPassReq.ProposalPassMessageReqDto;
import shop.mtcoding.miniproject.handler.ex.CustomApiException;
import shop.mtcoding.miniproject.handler.ex.CustomException;
import shop.mtcoding.miniproject.model.Company;
import shop.mtcoding.miniproject.model.CompanyRepository;
import shop.mtcoding.miniproject.model.CompanyScrap;
import shop.mtcoding.miniproject.model.CompanyScrapRepository;
import shop.mtcoding.miniproject.model.Person;
import shop.mtcoding.miniproject.model.PersonProposalRepository;
import shop.mtcoding.miniproject.model.PersonRepository;
import shop.mtcoding.miniproject.model.Post;
import shop.mtcoding.miniproject.model.PostRepository;
import shop.mtcoding.miniproject.model.Resume;
import shop.mtcoding.miniproject.model.ResumeRepository;
import shop.mtcoding.miniproject.model.Skill;
import shop.mtcoding.miniproject.model.SkillFilter;
import shop.mtcoding.miniproject.model.SkillFilterRepository;
import shop.mtcoding.miniproject.model.SkillRepository;
import shop.mtcoding.miniproject.model.User;
import shop.mtcoding.miniproject.model.UserRepository;
import shop.mtcoding.miniproject.service.CompanyService;
import shop.mtcoding.miniproject.service.PersonProposalService;
import shop.mtcoding.miniproject.service.PostService;
import shop.mtcoding.miniproject.service.ProposalPassService;
import shop.mtcoding.miniproject.util.EncryptionUtils;

@Controller
public class CompanyContoller {

    @Autowired
    private RedisTemplate<String, User> redisTemplate;

    @Autowired
    private HttpSession session;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private PostService postService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private PersonProposalRepository personProposalRepository;

    @Autowired
    private ResumeRepository resumeRepository;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private PersonProposalService personProposalService;
    @Autowired
    private ProposalPassService proposalPassService;

    @Autowired
    private CompanyScrapRepository companyScrapRepository;

    @Autowired
    private SkillFilterRepository skillFilterRepository;

    // 인증에 필요한 일이기 때문에 company/login 이 아닌 이어서 했습니다.
    @GetMapping("/companyLoginForm")
    public String companyLoginForm() {
        return "company/loginForm";
    }

    @PostMapping("companyLogin")
    public String companyLogin(LoginCompanyReqDto loginCompanyReqDto) {

        if (loginCompanyReqDto.getEmail() == null ||
                loginCompanyReqDto.getEmail().isEmpty()) {
            throw new CustomException("이메일을 작성해주세요");
        }

        if (loginCompanyReqDto.getPassword() == null ||
                loginCompanyReqDto.getPassword().isEmpty()) {
            throw new CustomException("패스워드를 작성해주세요");
        }
        User userCheck = userRepository.findByEmail(loginCompanyReqDto.getEmail());
        if (userCheck == null) {
            throw new CustomException("이메일 혹은 패스워드가 잘못입력되었습니다.");
        }
        // DB Salt 값
        String salt = userCheck.getSalt();
        // DB Salt + 입력된 password 해싱
        loginCompanyReqDto.setPassword(EncryptionUtils.encrypt(loginCompanyReqDto.getPassword(), salt));
        User principal = userRepository.findCompanyByEmailAndPassword(loginCompanyReqDto.getEmail(),
                loginCompanyReqDto.getPassword());
        if (principal == null) {
            throw new CustomException("이메일 혹은 패스워드가 잘못입력되었습니다.");
        }
        redisTemplate.opsForValue().set("principal", principal);
        session.setAttribute("principal", principal);

        return "redirect:/company/main";
    }

    @GetMapping("/companyJoinForm")
    public String companyJoinForm1(Model model) {
        EncryptionUtils.getSalt();
        model.addAttribute("companyReq", new JoinCompanyReqDto()); // UserForm: 사용자 정보를 담는 모델 클래스
        return "company/joinForm";
    }

    @PostMapping("/companyJoin")
    public String join(JoinCompanyReqDto joinCompanyReqDto) {

        if (joinCompanyReqDto.getName() == null ||
                joinCompanyReqDto.getName().isEmpty()) {
            throw new CustomException("회사명을 작성해주세요");
        }
        if (joinCompanyReqDto.getAddress() == null ||
                joinCompanyReqDto.getAddress().isEmpty()) {
            throw new CustomException("회사 주소를 작성해주세요");
        }
        if (joinCompanyReqDto.getNumber() == null ||
                joinCompanyReqDto.getNumber().isEmpty()) {
            throw new CustomException("사업자 번호를 작성해주세요");
        }

        if (joinCompanyReqDto.getManagerName() == null ||
                joinCompanyReqDto.getManagerName().isEmpty()) {
            throw new CustomException("담당자 성함을 작성해주세요");
        }

        if (joinCompanyReqDto.getEmail() == null ||
                joinCompanyReqDto.getEmail().isEmpty()) {
            throw new CustomException("담당자 이메일을 작성해주세요");
        }

        if (joinCompanyReqDto.getPassword() == null ||
                joinCompanyReqDto.getPassword().isEmpty()) {
            throw new CustomException("비밀번호를 작성해주세요");
        }

        companyService.join(joinCompanyReqDto);
        return "redirect:/companyLoginForm";
    }

    @GetMapping({ "/company/main", "/company" })
    public String companyMain() {
        return "company/main";
    }

    @GetMapping("/company/getResume")
    public String companyGetResume(Model model) {
        User userPS = (User) session.getAttribute("principal");
        List<CompanyProposalListRespDto> companyProposalList = personProposalRepository
                .findAllWithPostAndResumeAndPInfoByCInfoId(userPS.getCInfoId());

        List<CompanyProposalListDateRespDto> companyProposalList2 = new ArrayList<>();

        for (CompanyProposalListRespDto cpl : companyProposalList) {
            CompanyProposalListDateRespDto dto = new CompanyProposalListDateRespDto();
            dto.setCInfoId(cpl.getCInfoId());
            dto.setId(cpl.getId());
            dto.setName(cpl.getName());
            dto.setPInfoId(cpl.getPInfoId());
            dto.setPostId(cpl.getPostId());
            dto.setPtitle(cpl.getPtitle());
            dto.setRtitle(cpl.getRtitle());
            dto.setResumeId(cpl.getResumeId());
            dto.setStatus(cpl.getStatus());

            Timestamp createdAt = cpl.getCreatedAt();
            Date date = new Date(createdAt.getTime());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String proposaltime = sdf.format(date);

            dto.setCreatedAt(proposaltime);

            companyProposalList2.add(dto);
        }

        Company company = companyRepository.findById(userPS.getCInfoId());
        model.addAttribute("companyPS", company);
        model.addAttribute("companyProposalList", companyProposalList2);

        return "company/getResume";
    }

    @GetMapping("/company/resumeDetail/{id}")
    public String companyResumeDetail(@PathVariable int id, Model model) {
        User principal = (User) session.getAttribute("principal");
        if (principal == null) {
            throw new CustomException("인증이 되지 않았습니다", HttpStatus.UNAUTHORIZED);
        }

        Resume resumePS = resumeRepository.findById(id);
        if (resumePS == null) {
            throw new CustomException("없는 이력서엔 접근할 수 없습니다.");
        }
        // 기업의 공고에 지원이력이 있는 이력서인지 확인
        // 기업의 공고에 없는 이력서라면 제안하기 버튼을 아니라면 합격 불합격 버튼을 두자
        List<PersonProposalDetailRespDto> proposalList = personProposalRepository
                .findAllWithPostByCInfoIdAndResumeId(principal.getCInfoId(), id);
        if (proposalList.size() > 0) {
            // 해당 이력서로 같은회사 다른 공고에 지원했을 수도 있음.
            // proposalList.get(0).getPostId(); //postId를 이용해서 어케 해보자...

            model.addAttribute("proposal", proposalList);
        }
        Person personPS = personRepository.findById(resumePS.getPInfoId());
        User user = userRepository.findByPersonId(personPS.getId());
        Skill skillPS = skillRepository.findByPInfoId(resumePS.getPInfoId());
        model.addAttribute("resumeDetail", resumePS);
        model.addAttribute("personDetail", personPS);
        model.addAttribute("personUser", user);
        model.addAttribute("skillDetail", skillPS.getSkills().split(","));
        return "company/resumeDetail";
    }

    @PutMapping("/company/proposal/{id}")
    public @ResponseBody ResponseEntity<?> companyUpdateResume(@PathVariable int id,
            @RequestBody CompanyProposalStatusReqDto statusCode) {
        User userPS = (User) session.getAttribute("principal");

        personProposalService.제안수정하기(id, userPS.getCInfoId(), statusCode.getStatusCode());
        return new ResponseEntity<>(new ResponseDto<>(1, "이력서 확인 완료", null), HttpStatus.OK);
    }

    @GetMapping("/company/recommend")
    public String companyRecommend(Model model) {
        User principal = (User) session.getAttribute("principal");
        if (principal == null) {
            throw new CustomException("인증이 되지 않았습니다", HttpStatus.UNAUTHORIZED);
        }
        // 공고 + 스킬 찾기
        List<postIdAndSkillsDto> postAndSkillsList = postRepository.findPostIdAndSkills(principal.getCInfoId());

        List<ResumeWithPostInfoRecommendDto> resumeAndPostInfo = new ArrayList<>();

        for (postIdAndSkillsDto p : postAndSkillsList) {
            String[] skills = p.getSkills().split(",");
            List<SkillFilter> sFilters = new ArrayList<>();
            for (String skill : skills) {
                List<SkillFilter> s = skillFilterRepository.findSkillNameForCompany(skill);
                sFilters.addAll(s);
            }

            // resume id로 count
            HashMap<Integer, Integer> resumeIdAndCount = new HashMap<>();
            for (SkillFilter sf : sFilters) {

                resumeIdAndCount.put(sf.getResumeId(), resumeIdAndCount.getOrDefault(sf.getResumeId(), 0) + 1);

            }

            Set<Integer> key = resumeIdAndCount.keySet();
            HashMap<Integer, Integer> resumeIdAndCount2 = new HashMap<>();
            for (Integer k : key) {
                Integer count = resumeIdAndCount.getOrDefault(k, 0);

                if (count >= 2) {
                    resumeIdAndCount2.put(k, count);
                }

            }

            // 내림차순 정렬
            List<Entry<Integer, Integer>> resumeIdList = new ArrayList<>(resumeIdAndCount2.entrySet());
            Collections.sort(resumeIdList, new Comparator<Entry<Integer, Integer>>() {
                public int compare(Entry<Integer, Integer> c1, Entry<Integer, Integer> c2) {
                    return c2.getValue().compareTo(c1.getValue());
                }
            });

            // RESUME LIST
            List<ResumeRecommendArrDto> resumeList = new ArrayList<>();
            for (Entry<Integer, Integer> entry : resumeIdList) {
                ResumeRecommendDto resumePS = resumeRepository.findNameAndTitleAndSkills(entry.getKey());
                // System.out.println(entry.getKey());
                // System.out.println("테스트: " + resumePS.getName());
                String[] skill = resumePS.getSkills().split(",");
                ResumeRecommendArrDto dto = new ResumeRecommendArrDto();
                dto.setId(resumePS.getId());
                dto.setName(resumePS.getName());
                dto.setSkills(skill);
                dto.setTitle(resumePS.getTitle());

                CompanyScrap cs = companyScrapRepository.findByCInfoIdAndResumeId(principal.getCInfoId(),
                        dto.getId());
                if (cs == null) {
                    dto.setScrap(0);
                } else {
                    dto.setScrap(1);
                }

                resumeList.add(dto);
            }
            String title = postRepository.findById(p.getPostId()).getTitle();

            ResumeWithPostInfoRecommendDto resumeAndPost = new ResumeWithPostInfoRecommendDto();
            resumeAndPost.setPostId(p.getPostId());
            resumeAndPost.setTitle(title);
            resumeAndPost.setResumes(resumeList);

            resumeAndPostInfo.add(resumeAndPost);
            // postTitle.add(title);
        }
        // 스킬 이력서 매칭
        model.addAttribute("postInfoAndResumes", resumeAndPostInfo);
        // model.addAttribute("postTitle", postTitle);
        return "company/recommend";
    }

    @GetMapping("/company/info")
    public String companyInfo(Model model) {
        User principal = (User) session.getAttribute("principal");
        Company companyPS = companyRepository.findById(principal.getCInfoId());
        model.addAttribute("companyPS", companyPS);
        return "company/info";
    }

    @GetMapping("/company/updateInfoForm")
    public String companyUpdateInfoForm(Model model) {
        User principal = (User) session.getAttribute("principal");
        Company companyPS = companyRepository.findById(principal.getCInfoId());
        model.addAttribute("companyPS", companyPS);
        return "company/updateInfoForm";
    }

    @PostMapping("/company/updateInfo")
    public ResponseEntity<?> companyUpdateInfo(@ModelAttribute CompanyUpdateInfoDto companyUpdateInfoDto)
            throws IOException {
        User principal = (User) session.getAttribute("principal");

        if (companyUpdateInfoDto.getAddress() == null || companyUpdateInfoDto.getAddress().isEmpty()) {
            throw new CustomApiException("주소를 확인해주세요");
        }
        if (companyUpdateInfoDto.getBossName() == null || companyUpdateInfoDto.getBossName().isEmpty()) {
            throw new CustomApiException("대표자명을 확인해주세요");
        }
        if (companyUpdateInfoDto.getCyear() == null || companyUpdateInfoDto.getCyear().isEmpty()) {
            throw new CustomApiException("설립년도를 확인해주세요");
        }
        if (companyUpdateInfoDto.getManagerName() == null || companyUpdateInfoDto.getManagerName().isEmpty()) {
            throw new CustomApiException("담당자 이름을 확인해주세요");
        }
        if (companyUpdateInfoDto.getManagerPhone() == null || companyUpdateInfoDto.getManagerPhone().isEmpty()) {
            throw new CustomApiException("담당자 번호를 확인해주세요");
        }
        if (companyUpdateInfoDto.getSize() == null) {
            throw new CustomApiException("사원수를 확인해주세요");
        }
        String pw = EncryptionUtils.encrypt(companyUpdateInfoDto.getOriginPassword(), principal.getSalt());
        if (!pw.equals(principal.getPassword())) {
            throw new CustomApiException("비밀번호가 일치하지 않습니다!");
        }

        companyService.updateInfo(companyUpdateInfoDto);
        User principalPS = (User) userRepository.findById(principal.getId());

        redisTemplate.opsForValue().set("principal", principalPS);
        session.setAttribute("principal", principalPS);
        return new ResponseEntity<>(new ResponseDto<>(1, "기업 정보 수정 완료", null), HttpStatus.OK);
    }

    @GetMapping("/company/scrap")
    public String companyScrap(Model model) {
        User principal = (User) session.getAttribute("principal");
        if (principal == null) {
            throw new CustomException("인증이 되지 않았습니다", HttpStatus.UNAUTHORIZED);
        }
        List<CompanyScrapWithResumeInfoResDto> cScrapList = companyScrapRepository
                .findresumeTitleAndNameByCInfoId(principal.getCInfoId());
        List<CompanyScrapWithResumeInfoResArrDto> cScrapArrList = new ArrayList<>();

        for (CompanyScrapWithResumeInfoResDto scrap : cScrapList) {
            String[] skillArr = scrap.getSkills().split(",");
            CompanyScrapWithResumeInfoResArrDto cs = new CompanyScrapWithResumeInfoResArrDto();
            cs.setId(scrap.getId());
            cs.setResumeId(scrap.getResumeId());
            cs.setName(scrap.getName());
            cs.setTitle(scrap.getTitle());
            cs.setSkills(skillArr);
            cScrapArrList.add(cs);
        }

        model.addAttribute("scrapList", cScrapArrList);
        return "company/scrap";
    }

    @GetMapping("/company/posts")
    public String companyPosts(Model model) {
        User userPS = (User) session.getAttribute("principal");
        if (userPS == null) {
            throw new CustomException("인증이 되지 않았습니다.", HttpStatus.UNAUTHORIZED);
        }

        List<PostTitleRespDto> postTitleList = postRepository.findAllTitleByCInfoId(userPS.getCInfoId());

        model.addAttribute("postTitleList", postTitleList);
        model.addAttribute("size", postTitleList.size());

        return "company/posts";
    }

    @GetMapping("/company/postDetail/{id}")
    public String companyDetail(@PathVariable int id, Model model) {

        User userPS = (User) session.getAttribute("principal");
        if (userPS == null) {
            throw new CustomException("인증이 되지 않았습니다.", HttpStatus.UNAUTHORIZED);
        }

        Post postPS = (Post) postRepository.findById(id);
        if (postPS == null) {
            throw new CustomException("없는 공고 입니다.");
        }
        if (postPS.getCInfoId() != userPS.getCInfoId()) {
            throw new CustomException("게시글을 볼 권한이 없습니다.", HttpStatus.FORBIDDEN);
        }
        Company companyPS = (Company) companyRepository.findById(userPS.getCInfoId());
        Skill skillPS = (Skill) skillRepository.findByPostId(id);
        StringTokenizer skills = new StringTokenizer(skillPS.getSkills(), ",");

        // 공고 디테일 보기 //인증 및 권한체크
        model.addAttribute("post", postPS);
        model.addAttribute("company", companyPS);
        model.addAttribute("skills", skills);

        return "company/postDetail";
    }

    @GetMapping("/company/updatePostForm/{id}")
    public String companyUpdatePost(@PathVariable int id, Model model) {

        User userPS = (User) session.getAttribute("principal");
        if (userPS == null) {
            throw new CustomException("인증이 되지 않았습니다.", HttpStatus.UNAUTHORIZED);
        }

        Post postPS = (Post) postRepository.findById(id);
        if (postPS == null) {
            throw new CustomException("없는 공고 입니다.");
        }
        if (postPS.getCInfoId() != userPS.getCInfoId()) {
            throw new CustomException("게시글을 수정할 권한이 없습니다.", HttpStatus.FORBIDDEN);
        }

        Company companyPS = (Company) companyRepository.findById(userPS.getCInfoId());
        Skill skillPS = (Skill) skillRepository.findByPostId(id);

        // 공고 디테일 보기 //인증 및 권한체크
        model.addAttribute("post", postPS);
        model.addAttribute("company", companyPS);
        model.addAttribute("skillsPS", skillPS.getSkills());
        model.addAttribute("skills", Skill.madeSkills());
        return "company/updatePostForm";
    }

    @PutMapping("/company/updatePost/{id}")
    public @ResponseBody ResponseEntity<?> companyUpdatePost(@PathVariable int id,
            @RequestBody PostUpdateReqDto postUpdateReqDto) {
        User userPS = (User) session.getAttribute("principal");

        if (postUpdateReqDto.getTitle() == null ||
                postUpdateReqDto.getTitle().isEmpty()) {
            throw new CustomApiException("title 작성해주세요");
        }
        if (postUpdateReqDto.getPay() == null ||
                postUpdateReqDto.getPay().isEmpty()) {
            throw new CustomApiException("연봉 작성해주세요");
        }
        if (postUpdateReqDto.getCareer() == null) {
            throw new CustomApiException("지원 자격 작성해주세요");
        }
        if (postUpdateReqDto.getPay() == null ||
                postUpdateReqDto.getPay().isEmpty()) {
            throw new CustomApiException("연봉 작성해주세요");
        }
        if (postUpdateReqDto.getCondition() == null ||
                postUpdateReqDto.getCondition().isEmpty()) {
            throw new CustomApiException("근무조건 작성해주세요");
        }
        if (postUpdateReqDto.getDeadline() == null ||
                postUpdateReqDto.getDeadline().isEmpty()) {
            throw new CustomApiException("마감시간 작성해주세요");
        }
        if (postUpdateReqDto.getStartHour() == null ||
                postUpdateReqDto.getStartHour().isEmpty() || (postUpdateReqDto.getEndHour() == null ||
                        postUpdateReqDto.getEndHour().isEmpty())) {
            throw new CustomApiException("근무 시간을 작성해주세요");
        }
        if (postUpdateReqDto.getJobIntro() == null ||
                postUpdateReqDto.getJobIntro().isEmpty()) {
            throw new CustomApiException("업무소개를 작성해주세요");
        }
        if (postUpdateReqDto.getComIntro() == null ||
                postUpdateReqDto.getComIntro().isEmpty()) {
            throw new CustomApiException("기업소개를 작성해주세요");
        }
        if (postUpdateReqDto.getTitle().length() >= 20) {
            throw new CustomApiException("title의 길이가 20자 이하여야 합니다");
        }
        if (postUpdateReqDto.getSkills() == null || postUpdateReqDto.getSkills().isEmpty()) {
            throw new CustomApiException("기술스택은 최소1개 선택해 주십시오");
        }
        if (postUpdateReqDto.getComIntro().length() >= 200) {
            throw new CustomApiException("기업소개를 길이가 20자 이하여야 합니다");
        }
        if (postUpdateReqDto.getJobIntro().length() >= 200) {
            throw new CustomApiException("업무소개를 길이가 20자 이하여야 합니다");
        }
        if (postUpdateReqDto.getSkills().split(",").length > 5) {
            throw new CustomApiException("기술스택은 최대 5개 선택해 주십시오");
        }
        postService.공고수정하기(postUpdateReqDto, id, userPS.getCInfoId());

        return new ResponseEntity<>(new ResponseDto<>(1, "공고 수정 성공", null), HttpStatus.CREATED);
        // return "redirect:/company/postDetail/1"; // +id
    }

    @GetMapping("/company/savePostForm")
    public String companySavePostForm(Model model) {

        User userPS = (User) session.getAttribute("principal");
        if (userPS == null) {
            throw new CustomException("인증이 되지 않았습니다.", HttpStatus.UNAUTHORIZED);
        }

        Company companyPS = (Company) companyRepository.findById(userPS.getCInfoId());
        if (companyPS.getBossName() == null || companyPS.getBossName().isEmpty()
                || companyPS.getCyear() == null || companyPS.getLogo() == null ||
                companyPS.getLogo().isEmpty()
                || companyPS.getManagerPhone() == null ||
                companyPS.getManagerPhone().isEmpty()
                || companyPS.getSize() == null || companyPS.getCyear() == null) {
            return "redirect:/company/info";
        }

        model.addAttribute("company", companyPS);
        model.addAttribute("skills", Skill.madeSkills());
        return "company/savePostForm";
    }

    @PostMapping("/company/savePost")
    public String companySavePost(Model model, PostSaveReqDto postSaveReqDto) {

        User userPS = (User) session.getAttribute("principal");
        if (userPS == null) {
            throw new CustomException("인증이 필요합니다");
        }

        if (postSaveReqDto.getTitle() == null ||
                postSaveReqDto.getTitle().isEmpty()) {
            throw new CustomException("title 작성해주세요");
        }
        if (postSaveReqDto.getPay() == null ||
                postSaveReqDto.getPay().isEmpty()) {
            throw new CustomException("연봉 작성해주세요");
        }
        if (postSaveReqDto.getCareer() == null) {
            throw new CustomException("지원 자격 작성해주세요");
        }
        if (postSaveReqDto.getPay() == null ||
                postSaveReqDto.getPay().isEmpty()) {
            throw new CustomException("연봉 작성해주세요");
        }
        if (postSaveReqDto.getCondition() == null ||
                postSaveReqDto.getCondition().isEmpty()) {
            throw new CustomException("근무조건 작성해주세요");
        }
        if (postSaveReqDto.getDeadline() == null ||
                postSaveReqDto.getDeadline().isEmpty()) {
            throw new CustomException("마감시간 작성해주세요");
        }
        if (postSaveReqDto.getStartHour() == null ||
                postSaveReqDto.getStartHour().isEmpty() || (postSaveReqDto.getEndHour() == null ||
                        postSaveReqDto.getEndHour().isEmpty())) {
            throw new CustomException("근무 시간을 작성해주세요");
        }
        if (postSaveReqDto.getJobIntro() == null ||
                postSaveReqDto.getJobIntro().isEmpty()) {
            throw new CustomException("업무소개를 작성해주세요");
        }
        if (postSaveReqDto.getCIntro() == null ||
                postSaveReqDto.getCIntro().isEmpty()) {
            throw new CustomException("기업소개를 작성해주세요");
        }
        if (postSaveReqDto.getTitle().length() >= 20) {
            throw new CustomException("title의 길이가 20자 이하여야 합니다");
        }
        if (postSaveReqDto.getJobIntro().length() >= 200) {
            throw new CustomException("기업소개를 길이가 20자 이하여야 합니다");
        }
        if (postSaveReqDto.getCIntro().length() >= 200) {
            throw new CustomException("업무소개를 길이가 20자 이하여야 합니다");
        }

        // postinsert skillinsert 동시 진행
        int id = postService.공고등록(postSaveReqDto, userPS.getCInfoId());

        return "redirect:/company/postDetail/" + id; // +id
    }

    @DeleteMapping("/company/deletePost/{id}")
    public @ResponseBody ResponseEntity<?> companyDeletePost(@PathVariable int id) {
        User userPS = (User) session.getAttribute("principal");
        if (userPS == null) {
            throw new CustomApiException("인증이 되지 않았습니다.", HttpStatus.UNAUTHORIZED);
        }

        postService.공고삭제하기(id, userPS.getCInfoId());

        return new ResponseEntity<>(new ResponseDto<>(1, "공고 삭제 성공", null), HttpStatus.OK);
    }

    @PostMapping("/company/proposalPass/{id}")
    public @ResponseBody ResponseEntity<?> insertProposalPass(@PathVariable int id,
            @RequestBody ProposalPassMessageReqDto message) {
        User userPS = (User) session.getAttribute("principal");
        if (userPS == null) {
            throw new CustomApiException("인증이 되지 않았습니다.", HttpStatus.UNAUTHORIZED);
        }

        proposalPassService.메시지전달하기(id, userPS.getCInfoId(), message.getMessage());
        return new ResponseEntity<>(new ResponseDto<>(1, "메시지 전달 성공", null), HttpStatus.CREATED);
    }

    // @GetMapping("/resume/{id}")
    // public String personResumeDetail(@PathVariable int id, Model model,
    // HttpSession session) {
    // User principal = (User) session.getAttribute("principal");
    // if (principal == null) {
    // throw new CustomException("인증이 되지 않았습니다", HttpStatus.UNAUTHORIZED);
    // }

    // Resume resumePS = resumeRepository.findById(id);

    // Person personPS = personRepository.findById(resumePS.getPInfoId());
    // Skill skillPS = skillRepository.findByPInfoId(resumePS.getPInfoId());
    // model.addAttribute("resumeDetail", resumePS);
    // model.addAttribute("personDetail", personPS);
    // model.addAttribute("skillDetail", skillPS.getSkills().split(","));
    // return "person/resumeDetailForm";
    // }
}
