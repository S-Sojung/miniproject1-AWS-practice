<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ include file="../layout/header.jsp" %>

        <div class="container d-flex">
            <div class="list-group mx-2">
                <a href="/person/info" class="list-group-item shadow" style="width: 150px;">회원 정보</a>
                <a href="/person/resumes" class="list-group-item hs_list_effect shadow">이력서 관리</a>
                <a href="/person/scrap" class="list-group-item shadow" >스크랩</a>
                <a href="/person/history" class="list-group-item shadow">지원 이력</a>
            </div>
            
            <form action="/person/updateResume/${resumePS.id}" method="post" enctype="multipart/form-data"
                onvalid="chooseSkiil()" onsubmit="return onSubmit();">
                <div class=" mx-2 pb-4 w-100">
                    <div class="border border-tertiary w-100 p-5 rounded shadow">
                        <h1><input type="text" placeholder="이력서 제목을 입력하세요" style="width: 700px"  class="hs_input_title"
                                name="title" value="${resumePS.title}"></h1>
                          <span>공개 여부 설정 |</span>
                        <select name="publish">
                        <c:choose>
                           <c:when test="${resumPS.publish == true}">
                           <option value="true" selected>공개중</option>
                            <option value="false">비공개중</option>
                           </c:when>
                           <c:otherwise>
                              <option value="true">공개중</option>
                            <option value="false" selected>비공개중</option>
                           </c:otherwise>
                        </c:choose>
                         
                        </select>

                        <hr>

                        <div class="container" style="width: 85%;">
                            <div class="d-flex row justify-content-center">
                                <div class="jh_resume_flexbox mt-3">

                                    <img src="${resumePS.profile == null ? '/images/profile1.jpg' : resumePS.profile}"
                                        id="imagePreview" style="width: 200px; height: 250px;"
                                        class="mb-2 border border-tertiary">

                                    <div class="jh_resume_personal_info">
                                        <div class="input-group mb-3">

                                            <span class="input-group-text justify-content-center w-25 init_color hs_span">이름</span>

                                            <input type="text" class="form-control" style="width: 120px;"
                                                value="${personPS.name}" name="name">
                                        </div>

                                        <div class="input-group mb-3">

                                            <span class="input-group-text justify-content-center w-25 init_color hs_span">생년월일</span>

                                            <input type="date" class="form-control" style="width: 120px;"
                                                value="${personPS.birthday}" name="birthday">
                                        </div>

                                        <div class="input-group mb-3">
                                            <span class="input-group-text justify-content-center w-25 init_color hs_span">연락처</span>

                                            <input type="tel" class="form-control" value="${personPS.phone}"
                                                name="phone">
                                        </div>

                                        <div class="input-group mb-3">

                                            <span class="input-group-text justify-content-center w-25 init_color hs_span">주소</span>

                                            <input type="text" class="form-control" value="${personPS.address}"
                                                name="address">
                                        </div>

                                        <div class="input-group mb-3">

                                            <span class="input-group-text justify-content-center w-25 init_color hs_span">이메일</span>

                                            <input type="email" class="form-control" value="${principal.email}"
                                                readonly>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="container" style="width: 85%;">
                            <div class="row justify-content-center">
                                <div class="col-md-14 mx-auto">
                                    <input type="file" class="form-control mt-2" name="profile"
                                        onchange="chooseImage(this)" />
                                    <div class="mt-5">
                                        <h4>포트폴리오 주소</h4>
                                        <input type="text" class="form-control mt-2" id="floatingInput"
                                            placeholder="git 또는 blog 주소를 입력해주세요" name="portfolio"
                                            style="display: block;" value="${resumePS.portfolio}" required>
                                    </div>
                                </div>
                                <div class="my-5">
                                    <h4>기술스택</h4>
                                    <input type="hidden" value="${skillPS}" id="check">
                                    <div class="row row-cols-auto gap-3 justify-content-center my-3">
                                        <c:forEach items="${skills}" var="skill">
                                            <input type="checkbox" name="skills" class="btn-check" id="${skill}"
                                                value="${skill}" autocomplete="off">
                                            <label class="btn btn-outline-primary" for="${skill}">${skill}</label>
                                        </c:forEach>
                                    </div>
                                </div>

                                <div>
                                    <h4>자기소개</h4>
                                    <textarea name="selfIntro" class="w-100 opacity-50" rows="10"
                                        placeholder="내용을 입력하세요">${resumePS.selfIntro}</textarea>
                                </div>
                                <div class="d-flex mt-4 justify-content-center">
                                    <div class="px-2">
                                        <button type="submit" class="btn btn-dark">저장</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </form>
        </div>


        <script>
            function onSubmit() {
                return true;
            }


            let checkSkill = $("#check").val().split(",");
            checkSkill.forEach(element => {
                $("input:checkbox[id='" + element + "']").prop("checked", true);
            });

            function chooseSkiil() {
                skillList = [];

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

            }

            function chooseImage(obj) {

                let f = obj.files[0];
                if (!f.type.match("image")) {
                    alert("이미지 파일이 아닙니다!");
                }

                let reader = new FileReader();
                reader.readAsDataURL(f);
                reader.onload = function (e) {
                    $("#imagePreview").attr("src", e.target.result);
                }

            }
        </script>
        <%@ include file="../layout/footer.jsp" %>