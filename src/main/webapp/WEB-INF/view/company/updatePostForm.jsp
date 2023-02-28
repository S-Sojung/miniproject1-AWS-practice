<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../layout/header.jsp" %>

 <div class="container d-flex mt-4">
        <div class="list-group ms-2 mt-4">
            <a href="#" class="list-group-item">회사 정보</a>
            <a href="#" class="list-group-item hs_list_effect" style="width: 150px;">공고 관리</a>
            <a href="#" class="list-group-item">받은 이력서</a>
            <a href="#" class="list-group-item">스크랩한 유저</a>
        </div>

        <div class="container justify-content-center mt-4">
            <div class="d-flex justify-content-between w-100">

                <div class="border hs_content_box w-100 mx-2 rounded">
                    <div class="d-flex mt-5 mb-4 justify-content-center">
                        <h1 class="hs_line">공고 수정하기</h1>
                    </div>

                    <div class="px-5 pb-3">
                        <hr class="d-inline-flexw-100">
                    </div>

                    <div class="px-5">
                        <h5><b>공고명</b></h5>
                        <input class="form-control" type="text" style="width: 65%;">
                        <div class="pt-4">
                            <hr class="d-inline-flexw-100">
                        </div>

                        <div class="pt-2 mt-3">
                            <div class="border border-tertiary pt-3 d-inline-flex me-3 mb-3 w-100">
                                <table class="table table-borderless pt-5 ms-3">
                                    <tr class="pb-5">
                                        <th>지원 자격</th>
                                        <td><input class="form-control" type="text" style="width: 80%;"></td>
                                        <th>연봉</th>
                                        <td><input class="form-control" type="text" style="width: 80%;"></td>
                                    </tr>
                                    <tr>
                                        <th>근무 조건</th>
                                        <td><input class=" form-control" type="text" style="width: 80%;"></td>
                                        <th>근무 시간</th>
                                        <td><input class="form-control" type="text" style="width: 80%;"></td>
                                    </tr>
                                </table>
                            </div>

                            <div class="mt-4">
                                <h5><b>기업 소개</b></h5>
                                <div class="border border-tertiary p-3 d-inline-flex me-3 mb-3 w-100">
                                    <input class="form-control border-0" type="text">
                                </div>
                            </div>

                            <div class="mt-4">
                                <h5><b>업무 소개</b></h5>
                                <div class="border border-tertiary p-3 d-inline-flex me-3 mb-3 w-100">
                                    <input class="form-control border-0" type="text">
                                </div>
                            </div>

                            <div class="mt-4">
                                <h5><b>기술/자격 조건</b></h5>
                                <div class="p-3 d-inline-flex mb-3 w-100">
                                    <div class="row row-cols-auto gap-3 justify-content-center my-3">
                                        <input type="checkbox" class="btn-check" id="btn1" value="java"
                                            autocomplete="off">
                                        <label class="btn btn-outline-primary" for="btn1">java</label>
                                        <input type="checkbox" class="btn-check" id="btn2" value="spring"
                                            autocomplete="off">
                                        <label class="btn btn-outline-primary" for="btn2">spring</label>
                                        <input type="checkbox" class="btn-check" id="btn3" value="html"
                                            autocomplete="off">
                                        <label class="btn btn-outline-primary" for="btn3">html</label>
                                        <input type="checkbox" class="btn-check" id="btn4" value="javascript"
                                            autocomplete="off">
                                        <label class="btn btn-outline-primary" for="btn4">javascript</label>
                                        <input type="checkbox" class="btn-check" id="btn5" value="sql"
                                            autocomplete="off">
                                        <label class="btn btn-outline-primary" for="btn5">sql</label>
                                        <input type="checkbox" class="btn-check" id="btn6" value="android"
                                            autocomplete="off">
                                        <label class="btn btn-outline-primary" for="btn6">android</label>
                                        <input type="checkbox" class="btn-check" id="btn7" value="React"
                                            autocomplete="off">
                                        <label class="btn btn-outline-primary" for="btn7">React</label>
                                        <input type="checkbox" class="btn-check" id="btn8" value="Node.js"
                                            autocomplete="off">
                                        <label class="btn btn-outline-primary" for="btn8">Node.js</label>
                                        <input type="checkbox" class="btn-check" id="btn9" value="Express"
                                            autocomplete="off">
                                        <label class="btn btn-outline-primary" for="btn9">Express</label>
                                    </div>
                                </div>
                            </div>
                            <div class="mt-4 mb-4">
                                <h5><b>기업 정보</b></h5>
                                <div class="border border-tertiary ps-5 pe-2 pt-3 d-inline-flex mb-3 w-100">
                                    <table class="table table-borderless pt-5 ms-5">
                                        <tr class="pb-5">
                                                <th>사원수</th>
                                                <td>${company.size}</td>
                                                <th>대표자</th>
                                                <td>${company.bossName}</td>
                                            </tr>
                                            <tr>
                                                <th>설립년도</th>
                                                <td>${company.cyear}</td>
                                                <th>전화번호</th>
                                                <td>${company.managerPhone}</td>
                                            </tr>
                                            <tr>
                                                <th>주소</th>
                                                <td>${company.address}</td>
                                                <th>이메일</th>
                                                <td>${principal.email}</td>
                                            </tr>
                                    </table>
                                </div>
                            </div>
                            <div class="text-center mt-5 mb-5">
                                <button type="submit" class="btn btn-dark w-50">수정완료</button>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>



<%@ include file="../layout/footer.jsp" %>