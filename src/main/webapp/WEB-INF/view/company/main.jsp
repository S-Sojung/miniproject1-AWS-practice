<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ include file="../layout/header.jsp" %>
    <div>
        <!--배경 이미지 넣을 시-->
    </div>

    <div class="container px-4 text-center mt-5 mb-5 w-50">
        <div class="row gx-5">
            <div class="col p-4">
                <div class="card main_card" style="width:290px;">
                    <img src="/images/card.png" class="card-img-top">
                    <div class="card-body align-self-center">
                        <button type="button" class="btn btn-dark btn-lg" onclick="location.href='/company/posts';">등록 공고 관리</button>
                    </div>
                </div>
            </div>
            <div class="col p-4">
                <div class="card main_card" style="width:290px;">
                    <img src="/images/card.png" class="card-img-top">
                    <div class="card-body align-self-center">
                        <button type="button" class="btn btn-dark btn-lg" onclick="location.href='/company/getResume';">지원 받은 이력서</button>
                    </div>
                </div>

            </div>
        </div>
    </div>

    <%@ include file="../layout/footer.jsp" %>