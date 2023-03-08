<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ include file="../layout/header.jsp" %>
        <div class="container d-flex">
            <div class="list-group mx-2 ">
                <a href="/company/info" class="list-group-item shadow" style="width: 150px;">회사 정보</a>
                <a href="/company/posts" class="list-group-item hs_list_effect shadow">공고 관리</a>
                <a href="/company/getResume" class="list-group-item shadow">받은 이력서</a>
                <a href="/company/scrap" class="list-group-item shadow">스크랩한 유저</a>
            </div>

            <div class="justify-content-center mx-2 pb-4 w-100">
                <div class="border border-tertiary p-5 rounded shadow">
                    <h1 class="d-inline-flex">공고 등록하기</h1>
                    <hr>
                    <form action="/company/savePost" method="post" onsubmit="return Check()">
                        <div class="px-5 mt-5">
                            <h5><b>공고명</b></h5>
                            <input class="form-control" type="text" name="title" required>
                            <div class="pt-4">
                                <hr class="d-inline-flexw-100">
                            </div>

                            <div class="pt-2 mt-4">
                                <div class="border border-tertiary pt-3 d-inline-flex me-3 mb-3 w-100">
                                    <table class="table table-borderless pt-5 ms-3">
                                        <tr class="pb-5">
                                            <th>지원 자격</th>
                                            <td>
                                                <select name="career" class="jm_select selectpicker"
                                                    data-style="btn-info" style=" height: 35px;">
                                                    <option value="0" selected>신입</option>
                                                    <option value="1">1년차 이상</option>
                                                    <option value="3">3년차 이상</option>
                                                    <option value="5">5년차 이상</option>
                                                </select>
                                            </td>
                                            <th>연봉</th>
                                            <td>
                                                <select name="pay" class="jm_select selectpicker" data-style="btn-info"
                                                    style=" height: 35px;">
                                                    <option value="면접 후 결정" selected>면접 후 결정</option>
                                                    <option value="2000만원">2000만원</option>
                                                    <option value="3000만원">3000만원</option>
                                                    <option value="4000만원">4000만원</option>
                                                    <option value="5000만원 이상">5000만원 이상</option>
                                                </select>
                                            </td>
                                        </tr>
                                        <tr>
                                            <th>근무 조건</th>
                                            <td>
                                                <select name="condition" class="jm_select selectpicker"
                                                    data-style="btn-info" style=" height: 35px;">
                                                    <option value="계약직" selected>계약직</option>
                                                    <option value="정규직">정규직</option>
                                                </select>
                                            </td>
                                            <th>근무 시간</th>
                                            <td class="d-flex"><input class="form-control" type="time"
                                                    style="width: 38%;" name="startHour" id="startHour" value="09:00"
                                                    required>~
                                                <input class="form-control" type="time" style="width: 38%;"
                                                    name="endHour" id="endHour" value="18:00" required>
                                            </td>

                                        </tr>
                                    </table>
                                </div>
                                <div class="mt-4">
                                    <h5><b>마감 일자</b></h5>
                                    <div class="border border-tertiary p-3 d-inline-flex me-3 mb-3 w-100">
                                        <input class="form-control" type="date" id="deadline" name="deadline" required>
                                    </div>
                                </div>

                                <div class="mt-4">
                                    <h5><b>기업 소개</b></h5>
                                    <div class="border border-tertiary p-3 d-inline-flex me-3 mb-3 w-100">
                                        <input class="form-control border-0" type="text" name="cIntro" required>
                                    </div>
                                </div>

                                <div class="mt-4">
                                    <h5><b>업무 소개</b></h5>
                                    <div class="border border-tertiary p-3 d-inline-flex me-3 mb-3 w-100">
                                        <input class="form-control border-0" type="text" name="jobIntro" required>
                                    </div>
                                </div>

                                <div class="mt-4">
                                    <h5><b>기술/자격 조건</b></h5>
                                    <div class="p-3 d-inline-flex mb-3 w-100">
                                        <div class="row row-cols-auto gap-3 justify-content-center my-3">
                                            <c:forEach items="${skills}" var="skill">
                                                <input type="checkbox" name="skills" class="btn-check" id="${skill}"
                                                    value="${skill}" autocomplete="off">
                                                <label class="btn btn-outline-primary" for="${skill}">${skill}</label>
                                            </c:forEach>
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
                                    <button type="submit" class="btn btn-dark w-50">등록하기</button>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        </div>

        <script>
            let skillList = [];

            function Check() {
                skillList = [];
                if ($("#startHour").val() > $("#endHour").val()) {
                    alert("근무 시간이 잘못 설정되었습니다");
                    $("#startHour").focus();
                    return false;
                }

                let now = new Date();
                let deadline = new Date($("#deadline").val());
                if (deadline <= now) {
                    alert("마감날짜 현재날짜보다 빠를수 없습니다.");
                    $("#deadline").focus();
                    return false;
                }


                $('input:checkbox[name=skills]').each(function () {
                    if ($(this).is(":checked") == true) {
                        skillList.push($(this).val());
                    }
                });
                if (skillList.length >= 6) {
                    alert("기술 스택이 5개를 초과할 수 없습니다!");
                    $('input:checkbox[name=skills]').prop("checked", false);
                    // $('input:checkbox[name=checkSkills]').val().remove();
                    return false;
                }
                return ture;
            }
        </script>

        <%@ include file="../layout/footer.jsp" %>