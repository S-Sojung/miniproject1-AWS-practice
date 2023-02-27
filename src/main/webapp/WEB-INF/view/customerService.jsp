 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ include file="layout/header.jsp" %>
 
 <div class="container rounded-4 border border-tertiary mt-5 mb-5 justify-content-center"
        style="width: 1300px; height:850px;">
        <div class="row justify-content-center">
            <div class="col-12">
                <h2 class="text-center mt-5"><b>자주 묻는 질문</b></h2>
                <hr class="mx-auto mt-5" style="width: 1160px;">
            </div>
            <div class="row row-cols-2 mt-4 justify-content-center">
                <div class="col-6 border-borderless px-4" style="width: 600px;">
                    <div class="badge rounded-pill text-bg-secondary mt-5 mb-3 text-center border-borderless d-flex align-items-center justify-content-center"
                        style="height: 60px; font-size: 25px;"><b>개인 회원
                            FAQ</b>
                    </div>
                    <div class="accordion mb-5" id="accordionExample1">
                        <div class="accordion-item">
                            <h2 class="accordion-header" id="headingOne">
                                <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                                    data-bs-target="#collapseOne" aria-expanded="false" aria-controls="collapseOne">
                                    개인 질문1
                                </button>
                            </h2>
                            <div id="collapseOne" class="accordion-collapse collapse" aria-labelledby="headingOne"
                                data-bs-parent="#accordionExample">
                                <div class="accordion-body">
                                    개인 질문1에 대한 답변
                                </div>
                            </div>
                        </div>
                        <div class="accordion-item">
                            <h2 class="accordion-header" id="headingTwo">
                                <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                                    data-bs-target="#collapseTwo" aria-expanded="true" aria-controls="collapseTwo">
                                    개인 질문2
                                </button>
                            </h2>
                            <div id="collapseTwo" class="accordion-collapse collapse" aria-labelledby="headingTwo"
                                data-bs-parent="#accordionExample">
                                <div class="accordion-body">
                                    개인 질문2에 대한 답변
                                </div>
                            </div>
                        </div>
                        <div class="accordion-item">
                            <h2 class="accordion-header" id="headingThree">
                                <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                                    data-bs-target="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                                    개인 질문3
                                </button>
                            </h2>
                            <div id="collapseThree" class="accordion-collapse collapse" aria-labelledby="headingThree"
                                data-bs-parent="#accordionExample">
                                <div class="accordion-body">
                                    개인 질문3에 대한 답변
                                </div>
                            </div>
                        </div>
                        <div class="accordion-item">
                            <h2 class="accordion-header" id="headingFour">
                                <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                                    data-bs-target="#collapseFour" aria-expanded="false" aria-controls="collapseFour">
                                    개인 질문4
                                </button>
                            </h2>
                            <div id="collapseFour" class="accordion-collapse collapse" aria-labelledby="headingFour"
                                data-bs-parent="#accordionExample">
                                <div class="accordion-body">
                                    개인 질문4에 대한 답변
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-6 border-borderless px-4" style="width: 600px;">
                    <div class="badge rounded-pill text-bg-secondary mt-5 mb-3 text-center border-borderless d-flex align-items-center justify-content-center"
                        style="height: 60px; font-size: 25px;"><b>기업 회원
                            FAQ</b>
                    </div>
                    <div class="accordion mb-5" id="accordionExample2">
                        <div class="accordion-item">
                            <h2 class="accordion-header" id="headingOne2">
                                <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                                    data-bs-target="#collapseOne2" aria-expanded="false" aria-controls="collapseOne2">
                                    기업 질문1
                                </button>
                            </h2>
                            <div id="collapseOne2" class="accordion-collapse collapse" aria-labelledby="headingOne2"
                                data-bs-parent="#accordionExample">
                                <div class="accordion-body">
                                    기업 질문1에 대한 답변
                                </div>
                            </div>
                        </div>
                        <div class="accordion-item">
                            <h2 class="accordion-header" id="headingTwo2">
                                <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                                    data-bs-target="#collapseTwo2" aria-expanded="false" aria-controls="collapseTwo2">
                                    기업 질문2
                                </button>
                            </h2>
                            <div id="collapseTwo2" class="accordion-collapse collapse" aria-labelledby="headingTwo2"
                                data-bs-parent="#accordionExample">
                                <div class="accordion-body">
                                    기업 질문2에 대한 답변
                                </div>
                            </div>
                        </div>
                        <div class="accordion-item">
                            <h2 class="accordion-header" id="headingThree2">
                                <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                                    data-bs-target="#collapseThree2" aria-expanded="false"
                                    aria-controls="collapseThree2">
                                    기업 질문3
                                </button>
                            </h2>
                            <div id="collapseThree2" class="accordion-collapse collapse" aria-labelledby="headingThree2"
                                data-bs-parent="#accordionExample">
                                <div class="accordion-body">
                                    기업 질문3에 대한 답변
                                </div>
                            </div>
                        </div>
                        <div class="accordion-item">
                            <h2 class="accordion-header" id="headingFour2">
                                <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                                    data-bs-target="#collapseFour2" aria-expanded="false" aria-controls="collapseFour2">
                                    기업 질문4
                                </button>
                            </h2>
                            <div id="collapseFour2" class="accordion-collapse collapse" aria-labelledby="headingFour2"
                                data-bs-parent="#accordionExample">
                                <div class="accordion-body">
                                    기업 질문4에 대한 답변
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>


    <%@ include file="layout/footer.jsp" %>