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
                <c:choose>
                    <c:when test="${principal==null}">
                        <nav class="sj_full_container navbar bg-body-tertiary">
                            <div class="container">
                                <a class="navbar-brand fs-2" href="/">
                                    <img src="/images/INITilogo.png" alt="" width="50" height="40"
                                        class="d-inline-block align-text-top">
                                    INITi
                                </a>
                            </div>
                        </nav>
                    </c:when>
                    <c:when test="${principal.PInfoId!=0}">
                        <%-- 개인 로그인 --%>
                            <nav
                                class="sj_full_container navbar navbar-light bg-light  d-flex justify-content-around sticky-top border-bottom align-self-center">
                                <div class="container">
                                    <a class="navbar-brand fs-2" href="/person">
                                        <img src="/images/INITilogo.png" alt="" width="50" height="40"
                                            class="d-inline-block align-text-top">
                                        INITi
                                    </a>
                                    <ul class="nav link-dark" style="position: relative; bottom:-12px">
                                        <li class="nav-item border-bottom border-warning pb-2 border-3"
                                            id="main">
                                            <a class="nav-link active link-dark" aria-current="page"
                                                href="/person/main">채용공고</a>
                                        </li>
                                        <li class="nav-item border-bottom " id="recommend">
                                            <a class=" nav-link link-dark" href="/person/recommend">추천공고</a>
                                        </li>
                                        <li class="nav-item border-bottom ">
                                            <a class="nav-link link-dark" href="#">커뮤니티</a>
                                        </li>

                                        <li class="nav-item border-bottom ">
                                            <a class="nav-link link-dark" href="/logout">로그아웃</a>
                                        </li>
                                    </ul>

                                    <div>
                                        <a class="nav-link" href="/person/info"> username or Email</a>
                                    </div>
                                </div>
                                </div>
                            </nav>
                    </c:when>
                    <c:otherwise>
                        <%-- 기업 로그인 --%>
                            <nav
                                class="sj_full_container navbar navbar-light  d-flex justify-content-around  bg-light sticky-top border-bottom align-self-center">
                                <div class="container">
                                    <a class="navbar-brand fs-2" href="/company">
                                        <img src="/images/INITilogo.png" alt="" width="50" height="40"
                                            class="d-inline-block align-text-top">
                                        INITi
                                    </a>
                                    <ul class="nav link-dark" style="position: relative; bottom:-12px">
                                        <li class="nav-item border-bottom border-warning pb-2 border-3"
                                            id="recommend">
                                            <a class="nav-link link-dark" aria-current="page"
                                                href="/company/recommend">인재추천</a>
                                        </li>
                                        <li class="nav-item">
                                            <a class="nav-link link-dark" href="/logout">로그아웃</a>
                                        </li>
                                    </ul>

                                    <div>
                                        <a class="nav-link" href="/company/info"> username or Email</a>
                                    </div>
                                </div>
                                </div>
                            </nav>
                    </c:otherwise>
                </c:choose>
            </header>

            <script>
            $('.border-warning').removeClass('border-bottom border-warning border-3');
            if(location.href.includes('person')){
                let position = location.href.replace('http://localhost:8080/person/', '');
                if(position=="http://localhost:8080/person") position="main";
                $('#' + position).addClass('border-bottom border-warning border-3');
            }else{
                let position = location.href.replace('http://localhost:8080/company/', '');
                $('#' + position).addClass('border-bottom border-warning border-3');
            }

            </script>
            <div class="sj_full_container mb-5">
