<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ include file="../layout/header.jsp" %>


<div class="jm_body">

    <div class="sj_full_container">
        <div class="select_box jm_select_box mt-5">
            <select id="skill" class="jm_select selectpicker" data-style="btn-info" name="">
                <option value="none" selected>분야</option>
                <option value="Java">Java</option>
                <option value="Springboot">Springboot</option>
                <option value="C">C</option>
                <option value="CSS">CSS</option>
                <option value="html">Html</option>
                <option value="Flutter">Flutter</option>
                <option value="JavaScript">JavaScript</option>
            </select>
            <select id="career" class="jm_select" name="">
                <option value="none" selected>고용형태</option>
                <option value="신입">신입</option>
                <option value="경력">경력</option>
            </select>
            <select id="address" class="jm_select" name="">
                <option value="none" selected>근무지</option>
                <option value="경기">전국</option>
                <option value="경기">경기</option>
                <option value="서울">서울</option>
                <option value="부산">부산</option>
                <option value="경기">경남</option>
                <option value="제주">제주</option>
                <option value="울산">울산</option>
            </select>
            <select id="size" class="jm_select" name="">
                <option value="none" selected>규모</option>
                <option value="10_down">10명 이하</option>
                <option value="10_up">10명 이상</option>
                <option value="20">20명 이상</option>
                <option value="50">50명 이상</option>
                <option value="100">100명 이상</option>
            </select>
        </div>


        <div class="container jm_container mt-5">
            <div class="row row-cols-3 g-4 d-flex flex-wrap">
                <div class="col-xs-4">
                    <a href="#" style="color: inherit; text-decoration: none;">
                        <div class="card jm_card h-100">
                            <img src="/images/logo1.jpg" class="card-img-top jm_card_img_top">
                            <div class="card-body jm_card_body">
                                <div class="jm_company_name">다음</div>
                                <div class="jm_company_title"> 백엔드개발백엔드개발백엔드개발백엔드개발 </div>
                                <div class="jm_company_address">경기도 성남시</div>
                                <div class="jm_D-day d-flex justify-content-between">D-18
                                    <button type="button" class="btn btn-sm">
                                        <i class="fa-regular fa-thumbs-up fa-2xl"></i>
                                    </button>
                                </div>

                            </div>
                        </div>
                    </a>
                </div>
                <div class="col-xs-4">
                    <a href="#" style="color: inherit; text-decoration: none;">
                        <div class="card jm_card h-100">
                            <img src="/images/logo2.jfif" class="card-img-top jm_card_img_top">
                            <div class="card-body jm_card_body">
                                <div class="jm_company_name">던킨도너츠</div>
                                <div class="jm_company_title">웹 퍼플리셔 구합니다.</div>
                                <div class="jm_company_address">서울시 은평구</div>

                                <div class="jm_D-day d-flex justify-content-between">D-6
                                    <button type="button" class="btn btn-sm">
                                        <i class="fa-regular fa-thumbs-up fa-2xl"></i>
                                    </button>
                                </div>

                            </div>
                        </div>
                    </a>
                </div>
                <div class="col-xs-4">
                    <a href="#" style="color: inherit; text-decoration: none;">
                        <div class="card jm_card h-100">
                            <img src="/images/logo3.jfif" class="card-img-top jm_card_img_top">
                            <div class="card-body jm_card_body">
                                <div class="jm_company_name">나이키코리아</div>
                                <div class="jm_company_title">
                                    백엔드개발백엔드개발백엔드개발백엔드개발 </div>
                                <div class="jm_company_address">서울시 관악구</div>
                                <div class="jm_D-day d-flex  justify-content-between">D-24
                                    <button type="button" class="btn btn-sm">
                                        <i class="fa-regular fa-thumbs-up fa-2xl"></i>
                                    </button>
                                </div>
                            </div>
                        </div>
                    </a>
                </div>
                <div class="col-xs-4">
                    <a href="#" style="color: inherit; text-decoration: none;">
                        <div class="card jm_card h-100">
                            <img src="/images/logo4.svg" class="card-img-top jm_card_img_top">
                            <div class="card-body jm_card_body">
                                <div class="jm_company_name">그린컴퓨터아카데미</div>
                                <div class="jm_company_title">백엔드 개발자 구합니다.</div>
                                <div class="jm_company_address">부산시 진구</div>
                                <div class="jm_D-day d-flex  justify-content-between">D-18
                                    <button type="button" class="btn btn-sm">
                                        <i class="fa-regular fa-thumbs-up fa-2xl"></i>
                                    </button>
                                </div>
                            </div>
                        </div>
                    </a>
                </div>
            </div>

        </div>
    </div>
</div>

    <%@ include file="../layout/footer.jsp" %>