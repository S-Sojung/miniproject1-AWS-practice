package shop.mtcoding.miniproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

        Company sameCompany = companyRepository.findByCompanyNameAndNumber(joinCompanyReqDto.getName(),
                joinCompanyReqDto.getNumber());

        if (sameCompany != null) {
            throw new CustomException("이미 가입되어 있는 기업입니다.");
        }
        Company company = new Company();
        company.setName(joinCompanyReqDto.getName());
        company.setNumber(joinCompanyReqDto.getNumber());
        company.setAddress(joinCompanyReqDto.getAddress());
        company.setManagerName(joinCompanyReqDto.getManagerName());

        int result = companyRepository.insert(company);

        if (result != 1) {
            throw new CustomException("회원가입 실패", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        int result2 = userRepository.insert(joinCompanyReqDto.getEmail(), joinCompanyReqDto.getPassword(), 0,
                company.getId());

        if (result2 != 1) {
            throw new CustomException("회원가입 실패", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        // companyRepository.insert(company);
    }

}
