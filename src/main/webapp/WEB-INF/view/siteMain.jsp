<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <!DOCTYPE html>
        <html lang="en">

        <head>
            <title>INITi</title>
            <meta charset="utf-8">
            <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
            <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
            <script src="https://kit.fontawesome.com/32aa2b8683.js" crossorigin="anonymous"></script>
            <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css" />
            <link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.css" rel="stylesheet">
            <script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.js"></script>
            <link rel="stylesheet" href="/css/style.css">
        </head>

        <body>
            <header>
                <input type="hidden" id="current" val="siteMain" />
                <div class="mt-2">
                    <nav class="sj_full_container navbar bg-body-tertiary">
                        <div class="container mt-3" style="display: block; width: 100%; text-align: center;">
                            <a class="navbar-brand fs-2" href="/">
                                <img src="/images/INITilogo.png" alt="" width="90" height="60"
                                    class="d-inline-block align-text-top">
                                <h1>I N I T i</h1>
                                <h6>이니티와 함께 새출발 해볼까요?</h6>
                            </a>
                        </div>
                    </nav>
                </div>

                <div class="container px-4 mb-5 w-50">
                    <div class="d-flex justify-content-between">
                        <div>
                            <div class="card main_card" style="width:320px; height: 400px;">
                                <div class="d-flex align-items-center justify-content-center" style="height: 300px;">
                                    <img src="/images/user.png" class="card-img-top w-75">
                                </div>
                                <div class="card-body align-self-center">
                                    <button type="button" class="btn btn-dark btn-lg"
                                        onClick="location.href='/personLoginForm'">개인 회원</button>
                                </div>
                            </div>
                        </div>


                        <div>
                            <div class="card main_card" style="width:320px; height: 400px;">
                                <div class="d-flex align-items-center justify-content-center" style="height: 300px;">
                                    <img src="/images/company.png" class="card-img-top w-75">
                                </div>
                                <div class="card-body align-self-center">
                                    <button type="button" class="btn btn-dark btn-lg"
                                        onClick="location.href='/companyLoginForm'">기업 회원</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>