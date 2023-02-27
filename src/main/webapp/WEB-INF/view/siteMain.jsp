<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ include file="layout/header.jsp" %>

    <div class="container px-4 text-center mt-5 mb-5 w-50">
        <div class="row gx-5">
            <div class="col p-4">
                 <div class="card main_card" style="width:290px;">
                    <img src="/images/card.png" class="card-img-top">
                    <div class="card-body align-self-center">
                        <button type="button" class="btn btn-dark btn-lg" onClick="location.href='/personLoginForm'">개인 회원</button>
                    </div>
                </div>
            </div>
            <div class="col p-4">
                <div class="card main_card" style="width:290px;">
                    <img src="/images/card.png" class="card-img-top">
                    <div class="card-body align-self-center">
                        <button type="button" class="btn btn-dark btn-lg" onClick="location.href='/companyLoginForm'">기업 회원</button>
                    </div>
                </div>
            </div>
        </div>
    </div>


    <%@ include file="layout/footer.jsp" %>