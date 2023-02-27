<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ include file="../layout/header.jsp" %>

        <div class="container d-flex mt-4">
            <div class="list-group ms-2 mt-4">
                <a href="/person/info" class="list-group-item  hs_list_effect" style="width: 150px;">회원 정보</a>
                <a href="/person/resumes" class="list-group-item ">이력서 관리</a>
                <a href="/person/scrap" class="list-group-item ">스크랩</a>
                <a href="/person/history" class="list-group-item ">지원 이력</a>
            </div>



            <div class="ms-2 p-4">
                <div class="border border-tertiary w-100 p-5 rounded ">
                    <div class="d-flex justify-content-between">
                        <h1 class="hs_line">개인회원 수정</h1>
                        <button type="button" class="btn btn-dark hs_update_button" onclick="personUpdate()">저장</button>
                    </div>
                    <hr />
                    <div>
                        <div class="container w-50 mt-5 text ">
                            <form>
                                <div class="input-group mb-3">
                                    <span
                                        class="input-group-text bg-light justify-content-center hs_span_size">이름</span>
                                    <input type="text" class="form-control" value="${person.name}" id="name">
                                </div>
                                <div class="input-group mb-3">
                                    <span
                                        class="input-group-text bg-light justify-content-center hs_span_size">연락처</span>
                                    <input type="tel" class="form-control" value="${person.phone}" id="phone">
                                </div>
                                <div class="input-group mb-3">
                                    <span
                                        class="input-group-text bg-light justify-content-center hs_span_size">이메일</span>
                                    <input type="email" class="form-control" value="${principal.email}" id="email"
                                        readonly>
                                </div>
                                <div class="input-group mb-3">
                                    <span
                                        class="input-group-text bg-light justify-content-center hs_span_size">주소</span>
                                    <input type="text" class="form-control" value="${person.address}" id="address">
                                </div>
                                <div class="input-group mb-3">
                                    <span class="input-group-text bg-light justify-content-center hs_span_size">현재
                                        비밀번호</span>
                                    <input type="password" class="form-control" value="${principal.password}" readonly>
                                </div>
                                <%-- <span style="font-size:10px; color: grey;">비밀번호(영문자, 숫자, 특수문자 포함 8-20자 이내)</span>
                                    --%>
                                    <div class="input-group mb-3">
                                        <span
                                            class="input-group-text bg-light justify-content-center hs_span_size">비밀번호</span>
                                        <input type="password" class="form-control" id="changePassword">
                                    </div>
                                    <div class="input-group mb-3">
                                        <span class="input-group-text bg-light justify-content-center hs_span_size">비밀번호
                                            확인</span>
                                        <input type="password" class="form-control" id="checkChangePassword"
                                            onchange="checkingPassword()">
                                    </div>

                                    <div id="checkAlertBox"></div>

                                    <div class="my-5">
                                        <h4>기술스택</h4>
                                        <div class="row row-cols-auto gap-3 justify-content-center my-3 jh_form_group">
                                            <input type="checkbox" class="btn-check" name="checkSkills" id="btn1"
                                                value="java" autocomplete="off">
                                            <label class="btn btn-outline-primary" for="btn1">java</label>
                                            <input type="checkbox" class="btn-check" name="checkSkills" id="btn2"
                                                value="spring" autocomplete="off">
                                            <label class="btn btn-outline-primary" for="btn2">spring</label>
                                            <input type="checkbox" class="btn-check" name="checkSkills" id="btn3"
                                                value="html" autocomplete="off">
                                            <label class="btn btn-outline-primary" for="btn3">html</label>
                                            <input type="checkbox" class="btn-check" name="checkSkills" id="btn4"
                                                value="javascript" autocomplete="off">
                                            <label class="btn btn-outline-primary" for="btn4">javascript</label>
                                            <input type="checkbox" class="btn-check" name="checkSkills" id="btn5"
                                                value="sql" autocomplete="off">
                                            <label class="btn btn-outline-primary" for="btn5">sql</label>
                                            <input type="checkbox" class="btn-check" name="checkSkills" id="btn6"
                                                value="android" autocomplete="off">
                                            <label class="btn btn-outline-primary" for="btn6">android</label>
                                            <input type="checkbox" class="btn-check" name="checkSkills" id="btn7"
                                                value="React" autocomplete="off">
                                            <label class="btn btn-outline-primary" for="btn7">React</label>
                                            <input type="checkbox" class="btn-check" name="checkSkills" id="btn8"
                                                value="Node.js" autocomplete="off">
                                            <label class="btn btn-outline-primary" for="btn8">Node.js</label>
                                            <input type="checkbox" class="btn-check" name="checkSkills" id="btn9"
                                                value="Express" autocomplete="off">
                                            <label class="btn btn-outline-primary" name="checkSkills"
                                                for="btn9">Express</label>
                                        </div>
                                    </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <script>



            function checkingPassword() {
                let pw = $("#changePassword").val();
                let cpw = $("#checkChangePassword").val();

                if (pw == cpw) {
                    $("#checkAlertBox").empty();
                    let checkAlertBox = `<div class="alert alert-info">
                            <strong>Complete!</strong> 비밀번호 일치</div>`
                    $("#checkAlertBox").append(checkAlertBox);
                } else {
                    $("#checkAlertBox").empty();
                    let checkAlertBox = `<div class="alert alert-danger">
                     <strong>Check!</strong> 비밀번호 불일치 </div>`
                    $("#checkAlertBox").append(checkAlertBox);
                }
            }


            let skillList = [];

            function chooseSkiil() {
                skillList = [];

                $('input:checkbox[name=checkSkills]').each(function () {
                    if ($(this).is(":checked") == true) {
                        skillList.push($(this).val());
                    }
                });

                if (skillList.length >= 6) {
                    alert("5개를 초과할 수 없습니다!");
                    $('input:checkbox[name=checkSkills]').prop("checked", false);
                    // $('input:checkbox[name=checkSkills]').val().remove();
                    return false;
                }

            }

            function personUpdate() {

                let check = chooseSkiil();
                if (check == false) {
                    return false;
                }

                let skills = skillList.join();

                let data = {
                    "name": $("#name").val(),
                    "phone": $("#phone").val(),
                    "email": $("#email").val(),
                    "address": $("#address").val(),
                    "password": $("#changePassword").val(),
                    "skills": skills
                };

                $.ajax({
                    type: "put",
                    data: JSON.stringify(data),
                    dataType: "json",
                    url: "/person/updateInfo",
                    headers: {
                        "Content-Type": "application/json; charset=UTF-8"
                    }
                }).done((res) => {
                    alert(res.msg);
                    location.href = "/person/info"
                }).fail((err) => {
                    console.log(err);
                    alert(err.responseJSON.msg);
                });
            }

        </script>
        <%@ include file="../layout/footer.jsp" %>