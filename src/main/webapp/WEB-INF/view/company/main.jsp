<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ include file="../layout/header.jsp" %>

        <div style="margin-top: 150px; margin-bottom: 100px;">
            <div class="container px-4 my-5 w-50" style="height: 800px;">

                <div class="d-flex justify-content-between">

                    <div>
                        <a href="/company/posts" style="color: inherit; text-decoration: none;">
                            <div class="card main_card" style="width:320px; height: 400px;">
                                <div class="d-flex align-items-center justify-content-center ">
                                    <img src="/images/companymainposticon.png" class="card-img-top w-100 h-100">
                                </div>
                                <div class="card-body align-self-center mt-2">
                                    <h3>등록 공고 관리</h3>
                                </div>
                            </div>
                        </a>
                    </div>

                    <div>
                        <a href="/company/getResume" style="color: inherit; text-decoration: none; ">
                            <div class="card main_card" style="width:320px; height: 400px;">
                                <div class="d-flex align-items-center justify-content-center">
                                    <img src="/images/companymainresume.png" class="card-img-top w-100 h-100">
                                </div>
                                <div class="card-body align-self-center mt-2">
                                    <h3>지원 받은 이력서</h3>
                                </div>
                            </div>
                        </a>
                    </div>

                </div>

            </div>
        </div>
        <%@ include file="../layout/footer.jsp" %>