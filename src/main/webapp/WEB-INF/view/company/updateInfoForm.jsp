<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ include file="../layout/header.jsp" %>

        <div class="container d-flex mt-4">
            <div class="list-group ms-2 mt-4">
                <a href="/company/info" class="list-group-item hs_list_effect" style="width: 150px;">회사 정보</a>
                <a href="/company/posts" class="list-group-item">공고 관리</a>
                <a href="/company/getResume" class="list-group-item">받은 이력서</a>
                <a href="/company/scrap" class="list-group-item">스크랩한 유저</a>
            </div>

            <div class="ms-2 p-4">
                <div class="border border-tertiary w-100 p-5 rounded ">
                    <div class="d-flex justify-content-between">
                        <h1 class="hs_line">${companyPS.name} 정보</h1>
                    </div>
                    <hr>
                    <img src="${companyPS.logo == null ? '/images/defaultLogo.png' : companyPS.logo}" id="companyLogo"
                        class="rounded mx-auto d-block rounded-4 mt-5 my-3 w-25" style="width:300px; height:250px;">
                    <div>
                        <form id="companyUpdate" enctype="multipart/form-data">
                            <div class="container w-50 mt-5 text ">
                                <div class="input-group mb-3">
                                    <input type="file" class="form-control" value="${companyPS.logo}"
                                        id="ChangeCompanyLogo" onchange="chooseImage(this)" name="logo">
                                </div>
                                <div class="input-group mb-3">
                                    <span
                                        class="input-group-text bg-light justify-content-center hs_span_size">회사명</span>
                                    <input type="text" class="form-control" value="${companyPS.name}" name="name"
                                        readonly>
                                </div>
                                <div class="input-group mb-3">
                                    <span
                                        class="input-group-text bg-light justify-content-center hs_span_size">대표자</span>
                                    <input type=" text" class="form-control" value="${companyPS.bossName}"
                                        id="companyBossName" name="bossName">
                                </div>
                                <div class="input-group mb-3">
                                    <span
                                        class="input-group-text bg-light justify-content-center hs_span_size">사업자번호</span>
                                    <input type="text" class="form-control" value="${companyPS.number}" name="number"
                                        readonly>
                                </div>

                                <div class="input-group mb-3">
                                <c:choose>
                                   <c:when test="${companyPS.cyear != null}">
                                            <span
                                        class="input-group-text bg-light justify-content-center hs_span_size">설립년도</span>
                                    <input type="text" class="form-control" value="${companyPS.cyear}" id="companyYear"
                                        name="cyear">
                                   </c:when>
                                
                                   <c:otherwise>
                                      <span
                                        class="input-group-text bg-light justify-content-center hs_span_size">설립년도</span>
                                    <input type="date" class="form-control" id="companyYear"
                                        name="cyear">
                                   </c:otherwise>
                                </c:choose>
                                 </div>
                                 
                                <div class="input-group mb-3">
                                    <span
                                        class="input-group-text bg-light justify-content-center hs_span_size">주소</span>
                                    <input type="text" class="form-control" value="${companyPS.address}"
                                        id="companyAddress" name="address">
                                </div>
                                <div class="input-group mb-3">
                                    <span
                                        class="input-group-text bg-light justify-content-center hs_span_size">사원수</span>
                                    <input type="text" class="form-control" value="${companyPS.size}" id="companySize"
                                        name="size">
                                </div>

                                <div class="mt-5">
                                    <h4 class="border-bottom pb-4">담당자 정보</h4>

                                </div>
                                <br>
                                <div class="input-group mb-3">
                                    <span
                                        class="input-group-text bg-light justify-content-center hs_span_size">이름</span>
                                    <input type="text" class="form-control" value="${companyPS.managerName}"
                                        id="companyManagerName" name="managerName">
                                </div>
                                <div class="input-group mb-3">
                                    <span
                                        class="input-group-text bg-light justify-content-center hs_span_size">연락처</span>
                                    <input type="text" class="form-control" value="${companyPS.managerPhone}"
                                        id="companyManagerPhone" name="managerPhone">
                                </div>
                                <div class="input-group mb-3">
                                    <span
                                        class="input-group-text bg-light justify-content-center hs_span_size">이메일</span>
                                    <input type="text" class="form-control" value="${principal.email}" name="email"
                                        readonly>
                                </div>
                                <div class="input-group mb-3">
                                    <span class="input-group-text bg-light justify-content-center hs_span_size">현재
                                        비밀번호</span>
                                    <input type="password" class="form-control" value="${principal.password}" readonly>
                                </div>
                                <div class="input-group mb-3">
                                    <span
                                        class="input-group-text bg-light justify-content-center hs_span_size">비밀번호</span>
                                    <input type="password" class="form-control" id="changePassword" name="password">
                                </div>
                                <div class="input-group mb-3">
                                    <span class="input-group-text bg-light justify-content-center hs_span_size">비밀번호
                                        확인</span>
                                    <input type="password" class="form-control" id="checkChangePassword"
                                        onchange="checkingPassword()">
                                </div>

                                <div id="checkAlertBox"></div>

                                <div class="d-inline-flex align-self-center justify-content-center mt-5 w-100">
                                    <div class="text-center">
                                        <button type="button" class="btn btn-dark mx-2" style="width: 100px;"
                                            onclick="companyUpdate()">수정완료</button>
                                    </div>
                                    <div class="text-center">
                                        <button type="button" class="btn btn-danger mx-2" style="width: 100px;"
                                            onclick="back()">취소</button>
                                    </div>
                                </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        </div>

        <script>

            function back() {
                location.href = "/company/info";
            }

            let logo;

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


            function chooseImage(obj) {
                let f = obj.files[0];
                logo = f;

                if (!f.type.match("image.*")) {
                    alert("이미지를 등록해야 합니다.");
                    return;
                }

                let reader = new FileReader();
                reader.readAsDataURL(f);

                reader.onload = function (e) {
                    $("#companyLogo").attr("src", e.target.result);
                }

                // console.log(f.get)
            }


            function companyUpdate(params) {
                let formData = new FormData($("#companyUpdate")[0]);

                $.ajax({
                    type: "post",
                    url: "/company/updateInfo",
                    enctype: 'multipart/form-data',
                    data: formData,
                    dataType: "json",
                    processData: false,
                    contentType: false
                }).done((res) => {
                    alert(res.msg);
                    location.href = "/company/info";
                }).fail((err) => {
                    //console.log(err.responseJSON);
                    alert(err.responseJSON.msg);
                })

            }
        </script>

        <%@ include file="../layout/footer.jsp" %>