<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ include file="../layout/header.jsp" %>

 <div>
        <!--배경 이미지 넣을 시-->
    </div>
    <div class="container mt-5 w-75">
        <h4>등록한 공고와 매칭되는 구직자를 확인해보세요🌟</h4>
    </div>
    <div class="container mb-5 mt-3 w-75">
        <table class="table table-hover">
            <tr class=" table-dark">
                <th class="col-xs-2 px-5">이름</th>
                <th class="col-xs-5">이력서</th>
                <th class="col-xs-3">기술 스택</th>
                <th class="col-xs-1"></th>
            </tr>
            <tr>
                <td class="px-5">성소정</td>
                <td>성실한 지원자입니다</td>
                <td><span class="badge text-bg-info">JAVA</span>
                    <span class="badge text-bg-danger">FLUTTER</span>
                </td>
                <td class="text-end px-5">
                    <i class="fa-regular fa-thumbs-up fa-1x"></i>
                </td>
            </tr>
            <tr>
                <td class="px-5">장희선</td>
                <td>더 성실한 지원자입니다</td>
                <td><span class="badge text-bg-info">JAVA</span>
                    <span class="badge text-bg-success">JPA</span>
                </td>
                <td class="text-end px-5">
                    <i class="fa-solid fa-thumbs-up fa-1x"></i>
                </td>
                </tr>
                <tr>
                    <td class="px-5">오주혜</td>
                    <td>더더 성실한 지원자입니다</td>
                    <td><span class="badge text-bg-info">JAVA</span>
                        <span class="badge text-bg-secondary">SPRING</span>
                    </td>
                    <td class="text-end px-5">
                        <i class="fa-regular fa-thumbs-up fa-1x"></i>
                    </td>
                </tr>
                <tr>
                    <td class="px-5">김정민</td>
                    <td>더더더 성실한 지원자입니다</td>
                    <td><span class="badge text-bg-info">JAVA</span>
                        <span class="badge text-bg-danger">FLUTTER</span>
                        <span class="badge text-bg-success">JPA</span>
                    </td>
                    <td class="text-end px-5">
                        <i class="fa-solid fa-thumbs-up fa-1x"></i>
                    </td>
                </tr>
        </table>
    </div>


    <%@ include file="../layout/footer.jsp" %>