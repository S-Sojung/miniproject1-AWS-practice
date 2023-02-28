package shop.mtcoding.miniproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import lombok.Setter;
import shop.mtcoding.miniproject.dto.company.CompanyReq.JoinCompanyReqDto;
import shop.mtcoding.miniproject.handler.ex.CustomException;
import shop.mtcoding.miniproject.model.Company;
import shop.mtcoding.miniproject.model.CompanyRepository;
import shop.mtcoding.miniproject.model.UserRepository;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    UserRepository userRepository;

    @Transactional
    public void join(JoinCompanyReqDto joinCompanyReqDto) {
        Company company = new Company();
        company.setName(joinCompanyReqDto.getName());
        int result = companyRepository.insert(company);

        Company sameCompany = companyRepository.findByCompanyNameAndNumber(joinCompanyReqDto.getName(),
                joinCompanyReqDto.getNumber());
        if (sameCompany != null) {
            throw new CustomException("이미 가입되어 있는 기업입니다.");
        }

        if (result != 1) {
            throw new CustomException("회원가입 실패", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        int result2 = userRepository.insert(joinCompanyReqDto.getEmail(), joinCompanyReqDto.getPassword(), 0,
                company.getId());

        companyRepository.insert(company);
    }

}
