<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ include file="../layout/header.jsp" %>

<div class="container d-flex mt-4">
        <div class="list-group ms-2 mt-4">
            <a href="/company/info" class="list-group-item" style="width: 150px;">회사 정보</a>
            <a href="/company/posts" class="list-group-item">공고 관리</a>
            <a href="/company/getResume" class="list-group-item">받은 이력서</a>
            <a href="/company/scrap" class="list-group-item hs_list_effect">스크랩한 유저</a>
        </div>

        <div class="ms-2 p-4">
            <div class="border border-tertiary w-100 p-5 rounded ">
                <div class="d-flex justify-content-between">
                    <h1 class="hs_line">스크랩한 지원자를 확인해보세요🎯</h1>
                </div>
                <hr>

                <div class="container mb-5 mt-5 w-100">
                    <table class="table table-hover">
                        <tr class=" table-dark">
                            <th class="col-2 px-5">이름</th>
                            <th class="col-5">이력서</th>
                            <th class="col-3">기술 스택</th>
                        </tr>
                        <tr>
                            <td class="px-5">성소정</td>
                            <td><a href="#" class="text-decoration-none">성실한 지원자입니다</a></td>
                            <td><span class="badge text-bg-info">JAVA</span>
                                <span class="badge text-bg-danger">FLUTTER</span>
                            </td>
                        </tr>
                        <tr">
                            <td class="px-5">장희선</td>
                            <td><a href="#" class="text-decoration-none">성실한 지원자입니다</a></td>
                            <td><span class="badge text-bg-info">JAVA</span>
                                <span class="badge text-bg-success">JPA</span>
                            </td>
                            </tr>
                            <tr>
                                <td class="px-5">오주혜</td>
                                <td><a href="#" class="text-decoration-none">성실한 지원자입니다</a></td>
                                <td><span class="badge text-bg-info">JAVA</span>
                                    <span class="badge text-bg-secondary">SPRING</span>
                                </td>
                            </tr>
                            <tr>
                                <td class="px-5">김정민</td>
                                <td><a href="#" class="text-decoration-none">성실한 지원자입니다</a></td>
                                <td><span class="badge text-bg-info">JAVA</span>
                                    <span class="badge text-bg-danger">FLUTTER</span>
                                    <span class="badge text-bg-success">JPA</span>
                                </td>
                            </tr>
                    </table>
                </div>
            </div>
        </div>
    </div>


    <%@ include file="../layout/footer.jsp" %>