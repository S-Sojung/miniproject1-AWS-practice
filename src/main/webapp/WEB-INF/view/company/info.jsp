<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ include file="../layout/header.jsp" %>

<div class="container d-flex mt-4">
        <div class="list-group ms-2 mt-4">
            <a href="/company/info" class="list-group-item  hs_list_effect" style="width: 150px;">회사 정보</a>
            <a href="/company/posts" class="list-group-item">공고 관리</a>
            <a href="/company/getResume" class="list-group-item">받은 이력서</a>
            <a href="/company/scrap" class="list-group-item ">스크랩한 유저</a>
        </div>

          <div class="ms-2 p-4">
            <div class="border border-tertiary w-100 p-5 rounded ">
                <div class="d-flex justify-content-between">
                    <h1 class="hs_line">KAKAO 정보</h1>
                    <button type="button" class="btn btn-dark hs_update_button"  onclick="location.href=`/company/updateInfoForm`;">수정하기</button>
                </div>


                <hr>
                <img src="/images/kakao.jpg" class="rounded mx-auto d-block rounded-4 mt-5 my-3 w-25">

                <div>
                    <div class="container w-50 mt-5 text ">
                        <form>
                            <div class="input-group mb-3">
                                <span class="input-group-text bg-light justify-content-center hs_span_size">회사명</span>
                                <input type="text" class="form-control" value="카카오" readonly>
                            </div>
                            <div class="input-group mb-3">
                                <span class="input-group-text bg-light justify-content-center hs_span_size">대표자</span>
                                <input type=" text" class="form-control" value="춘식이" readonly>
                            </div>
                            <div class="input-group mb-3">
                                <span class="input-group-text bg-light justify-content-center hs_span_size">사업자번호</span>
                                <input type="text" class="form-control" value="1111-8282-7979-7777" readonly>
                            </div>
                            <div class="input-group mb-3">
                                <span class="input-group-text bg-light justify-content-center hs_span_size">연락처</span>
                                <input type="text" class="form-control" value="02-1111-8888" readonly>
                            </div>
                            <div class="input-group mb-3">
                                <span class="input-group-text bg-light justify-content-center hs_span_size">설립년도</span>
                                <input type="text" class="form-control" value="2023.02.24" readonly>
                            </div>
                            <div class="input-group mb-3">
                                <span class="input-group-text bg-light justify-content-center hs_span_size">사원수</span>
                                <input type="text" class="form-control" value="10명" readonly>
                            </div>

                            <div class="mt-5">
                                <h4 class="border-bottom pb-4">담당자 정보</h4>

                            </div>
                            <br>
                            <div class="input-group mb-3">
                                <span class="input-group-text bg-light justify-content-center hs_span_size">이름</span>
                                <input type="text" class="form-control" value="어피치" readonly>
                            </div>
                            <div class="input-group mb-3">
                                <span class="input-group-text bg-light justify-content-center hs_span_size">연락처</span>
                                <input type="text" class="form-control" value="010-8282-7979" readonly>
                            </div>
                            <div class="input-group mb-3">
                                <span class="input-group-text bg-light justify-content-center hs_span_size">이메일</span>
                                <input type="text" class="form-control" value="my-kakao@naver.com" readonly>
                            </div>

                        </form>

                    </div>
                </div>
            </div>
        </div>
    </div>


    <%@ include file="../layout/footer.jsp" %>