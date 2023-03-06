package shop.mtcoding.miniproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import shop.mtcoding.miniproject.handler.ex.CustomApiException;
import shop.mtcoding.miniproject.model.CompanyScrap;
import shop.mtcoding.miniproject.model.CompanyScrapRepository;

@Service
@Transactional
public class CompanyScrapService {

    @Autowired
    private CompanyScrapRepository companyScrapRepository;

    public void delete(int id, int cInfoId) {

        CompanyScrap csPS = companyScrapRepository.findById(id);
        if (csPS == null) {
            throw new CustomApiException("스크랩 이력이 존재하지 않습니다!", HttpStatus.UNAUTHORIZED);
        }

        if (csPS.getCInfoId() != cInfoId) {
            throw new CustomApiException("취소 권한이 없습니다!", HttpStatus.FORBIDDEN);
        }

        int result = companyScrapRepository.deleteById(id);
        if (result != 1) {
            throw new CustomApiException("서버 오류!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
