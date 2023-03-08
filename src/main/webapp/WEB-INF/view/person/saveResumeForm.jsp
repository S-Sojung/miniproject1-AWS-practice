<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ include file="../layout/header.jsp" %>

        <div class="container d-flex">
            <div class="list-group mx-2">
                <a href="/person/info" class="list-group-item shadow" style="width: 150px;">회원
                    정보</a>
                <a href="/person/resumes" class="list-group-item hs_list_effect shadow">이력서
                    관리</a>
                <a href="/person/scrap" class="list-group-item shadow">스크랩</a>
                <a href="/person/history" class="list-group-item shadow">지원 이력</a>
            </div>

           <div class=" mx-2 pb-4 w-100">
                <form action="/person/resumes" method="post" enctype="multipart/form-data">

                    <div class="border border-tertiary w-100 p-5 rounded shadow">

                        <h1><input type="text" placeholder="이력서 제목을 입력하세요" style="width: 700px"
                                class="hs_input_title" name="title"></h1>
                        <span>공개 여부 설정 &nbsp | &nbsp </span>
                        <select name="publish">
                            <option value="true">공개중</option>
                            <option value="false">비공개중</option>
                        </select>

                        <hr>
                        <div class="container" style="width: 85%;">
                            <div class="d-flex row justify-content-center">

                                <div class="jh_resume_flexbox mt-3">
                                    <img src="/images/profile1.jpg" id="imagePreview"
                                        style="width: 200px; height: 250px;" class="mb-2 border border-tertiary">
                                    <div class="jh_resume_personal_info">
                                        <div class="input-group mb-3">
                                            <span
                                                class="input-group-text w-25 justify-content-center init_color hs_span">이름</span>
                                            <input type="text" class="form-control" style="width: 120px;"
                                                value="${personPS.name}" name="name">
                                        </div>

                                        <div class="input-group mb-3">

                                            <span
                                                class="input-group-text w-25 justify-content-center init_color hs_span">생년월일</span>

                                            <input type="date" class="form-control" style="width: 120px;"
                                                value="${personPS.birthday}" name="birthday">
                                        </div>

                                        <div class="input-group mb-3">


                                            <span
                                                class="input-group-text w-25 justify-content-center init_color hs_span">연락처</span>

                                            <input type="tel" class="form-control" value="${personPS.phone}"
                                                name="phone">
                                        </div>

                                        <div class="input-group mb-3">


                                            <span
                                                class="input-group-text w-25 justify-content-center init_color hs_span">주소</span>
                                            <input type="text" class="form-control" value="${personPS.address}"
                                                name="address">
                                        </div>

                                        <div class="input-group mb-3">

                                            <span
                                                class="input-group-text w-25 justify-content-center init_color hs_span">이메일</span>
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
                                            style="display: block;" required>
                                    </div>
                                </div>
                                <div class="my-5">
                                    <h4>기술스택 (최대 5개 선택)</h4>
                                    <div class="row row-cols-auto gap-3 justify-content-center my-3">
                                        <input type="checkbox" class="btn-check" id="btn1" value="java" name="skills"
                                            autocomplete="off" onclick="countCheck(this)">
                                        <label class="btn btn-outline-primary" for="btn1">java</label>
                                        <input type="checkbox" class="btn-check" id="btn2" value="spring" name="skills"
                                            autocomplete="off" onclick="countCheck(this)">
                                        <label class="btn btn-outline-primary" for="btn2">spring</label>
                                        <input type="checkbox" class="btn-check" id="btn3" value="html" name="skills"
                                            autocomplete="off" onclick="countCheck(this)">
                                        <label class="btn btn-outline-primary" for="btn3">html</label>
                                        <input type="checkbox" class="btn-check" id="btn4" value="javascript"
                                            name="skills" autocomplete="off" onclick="countCheck(this)">
                                        <label class="btn btn-outline-primary" for="btn4">javascript</label>
                                        <input type="checkbox" class="btn-check" id="btn5" value="sql" name="skills"
                                            autocomplete="off" onclick="countCheck(this)">
                                        <label class="btn btn-outline-primary" for="btn5">sql</label>
                                        <input type="checkbox" class="btn-check" id="btn6" value="android" name="skills"
                                            autocomplete="off" onclick="countCheck(this)">
                                        <label class="btn btn-outline-primary" for="btn6">android</label>
                                        <input type="checkbox" class="btn-check" id="btn7" value="React" name="skills"
                                            autocomplete="off" onclick="countCheck(this)">
                                        <label class="btn btn-outline-primary" for="btn7">React</label>
                                        <input type="checkbox" class="btn-check" id="btn8" value="Node.js" name="skills"
                                            autocomplete="off" onclick="countCheck(this)">
                                        <label class="btn btn-outline-primary" for="btn8">Node.js</label>
                                        <input type="checkbox" class="btn-check" id="btn9" value="Express" name="skills"
                                            autocomplete="off" onclick="countCheck(this)">
                                        <label class="btn btn-outline-primary" for="btn9">Express</label>
                                    </div>
                                </div>
                                <div>
                                    <h4>자기소개</h4>
                                    <textarea name="selfIntro" class="w-100 opacity-50" rows="10"
                                        placeholder="내용을 입력하세요"></textarea>
                                </div>
                                <div class="d-flex mt-4 justify-content-center">
                                    <div class="px-2">
                                        <button type="submit" class="btn btn-dark">저장</button>
                                    </div>
                                </div>
                            </div>

                        </div>

                </form>
            </div>

        </div>
        <script>
            function countCheck(obj) {
                let count = $("input:checked[type='checkbox']").length;

                if (count > 5) {
                    $(this).prop("checked", false);
                    alert("5개까지만 선택해주세요");
                    return false;
                }
                console.log(obj.value);
            }

            function chooseImage(obj) {
                console.log(obj);
                console.log(obj.files);
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