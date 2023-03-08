<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ include file="layout/header.jsp" %>

        <div class="container rounded-4 border border-tertiary mt-5 mb-5 justify-content-center shadow"
            style="width: 1300px; height:1000px;">
            <div class="row justify-content-center">
                <div class="col-12">
                    <h2 class="text-center mt-5"><b>자주 묻는 질문</b></h2>
                    <hr class="mx-auto mt-5" style="width: 1160px;">
                </div>
                <div class="row row-cols-2 mt-4 justify-content-center">
                    <div class="col-6 border-borderless px-4" style="width: 600px;">
                        <div class="badge rounded-pill init_color text-dark mt-5 mb-3 text-center border-borderless d-flex align-items-center justify-content-center"
                            style="height: 60px; font-size: 25px;"><b>개인 회원
                                FAQ</b>
                        </div>
                        <div class="accordion mb-5" id="accordionExample1">
                            <c:forEach items="${personFAQ}" var="pFAQ" varStatus="loop">
                                <div class="accordion-item">
                                    <h2 class="accordion-header" id="person-heading${loop.index}">
                                        <button class="accordion-button collapsed" type="button"
                                            data-bs-toggle="collapse" data-bs-target="#person-collapse${loop.index}"
                                            aria-expanded="false" aria-controls="person-collapse${loop.index}">
                                            ${pFAQ.question}
                                        </button>
                                    </h2>
                                    <div id="person-collapse${loop.index}" class="accordion-collapse collapse"
                                        aria-labelledby="person-heading${loop.index}"
                                        data-bs-parent="#accordionExample">
                                        <div class="accordion-body">
                                            ${pFAQ.answer}
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                    <div class="col-6 border-borderless px-4" style="width: 600px;">
                        <div class="badge rounded-pill mt-5 mb-3 init_color text-dark text-center border-borderless d-flex align-items-center justify-content-center"
                            style="height: 60px; font-size: 25px;"><b>기업 회원
                                FAQ</b>
                        </div>
                        <div class="accordion mb-5" id="accordionExample2">
                            <c:forEach items="${companyFAQ}" var="cFAQ" varStatus="loop">
                                <div class="accordion-item">
                                    <h2 class="accordion-header" id="company-heading${loop.index}">
                                        <button class="accordion-button collapsed" type="button"
                                            data-bs-toggle="collapse" data-bs-target="#company-collapse${loop.index}"
                                            aria-expanded="false" aria-controls="company-collapse${loop.index}">
                                            ${cFAQ.question}
                                        </button>
                                    </h2>
                                    <div id="company-collapse${loop.index}" class="accordion-collapse collapse"
                                        aria-labelledby="company-heading${loop.index}"
                                        data-bs-parent="#accordionExample">
                                        <div class="accordion-body">
                                            ${cFAQ.answer}
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </div>
        </div>


        <%@ include file="layout/footer.jsp" %>