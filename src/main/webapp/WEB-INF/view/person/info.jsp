<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ include file="../layout/header.jsp" %>

        <div class="container d-flex">
            <div class="list-group mx-2">
                <a href="/person/info" class="list-group-item hs_list_effect shadow" style="width: 150px;">회원 정보</a>
                <a href="/person/resumes" class="list-group-item shadow">이력서 관리</a>
                <a href="/person/scrap" class="list-group-item shadow">스크랩</a>
                <a href="/person/history" class="list-group-item shadow">지원 이력</a>
            </div>



            <div class=" mx-2 pb-4 w-100">
                <div class=" border border-tertiary p-5 rounded shadow">
                    <div class="d-flex justify-content-between">
                        <h1 class=" d-inline-flex">${person.name}의 정보</h1>
                        <button type="button" class="btn btn-dark" style="width:100px; height:40px;"
                            onclick="location.href=`/person/updateInfoForm`;">수정하기</button>
                    </div>
                    <hr />
                    <div>
                        <div class="container w-50 mt-5 text ">
                            <form>
                                <div class="input-group mb-3">
                                    <span
                                        class="input-group-text justify-content-center hs_span_size init_color hs_span">이름</span>
                                    <input type="text" class="form-control" value="${person.name}" readonly>
                                </div>
                                <div class="input-group mb-3">
                                    <span
                                        class="input-group-text justify-content-center hs_span_size init_color hs_span">생년월일</span>
                                    <input type="text" class="form-control" value="${person.birthday}" id="birthday"
                                        readonly>
                                </div>
                                <div class="input-group mb-3">
                                    <span
                                        class="input-group-text justify-content-center hs_span_size init_color hs_span">연락처</span>
                                    <input type="text" class="form-control" value="${person.phone}" readonly>
                                </div>
                                <div class="input-group mb-3">
                                    <span
                                        class="input-group-text justify-content-center hs_span_size init_color hs_span">주소</span>
                                    <input type="text" class="form-control" value="${person.address}" readonly>
                                </div>
                                <div class="input-group mb-3">
                                    <span
                                        class="input-group-text justify-content-center hs_span_size init_color hs_span">이메일</span>
                                    <input type="text" class="form-control" value="${principal.email}" readonly>
                                </div>

                                <hr class="my-4">
                                <div class="mt-5 jh_resume_skill">
                                    <h4>기술스택</h4>
                                    <div class="">
                                        <ul>
                                            <c:forEach items="${pSkillArr}" var="pSkill">
                                                <li>${pSkill}</li>
                                            </c:forEach>

                                        </ul>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script>
            $("#birthday").val($("#birthday").val().split(' ')[0]);
        </script>
        <%@ include file="../layout/footer.jsp" %>