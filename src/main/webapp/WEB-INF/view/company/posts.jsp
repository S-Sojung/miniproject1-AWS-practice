<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ include file="../layout/header.jsp" %>
 <div class="container d-flex mt-4">
        <div class="list-group ms-2 mt-4">
            <a href="/company/info" class="list-group-item" style="width: 150px;">회사 정보</a>
            <a href="/company/posts" class="list-group-item hs_list_effect">공고 관리</a>
            <a href="/company/getResume" class="list-group-item">받은 이력서</a>
            <a href="/company/scrap" class="list-group-item ">스크랩한 유저</a>
        </div>

        <div class="ms-2 p-4">
            <div class="border border-tertiary w-100 p-5 rounded ">
                <h1 class="hs_line">등록한 공고</h1>
                <hr>
                <div class="jh_resume mt-5">
                    <button class="jh_resume_button mb-5 rounded bg-light" onclick="location.href=`/company/savePostForm`;">➕ 새로운 공고
                        등록</button>
                    
                    <c:forEach items="${postTitleList}" var="post" varStatus="status">
                    <div class="jh_resume_content mb-3" style="display: flex; justify-content: space-between">
                    <div style="">
                        <a href="/company/postDetail/${post.id}">${post.title}</a>
                        <div id="post_parse">마감 기한 : ~${post.deadline}</div>
                    </div>
                    <div style="display: flex;">
                        <button type="button" class="btn btn-secondary" onclick="deleteById(${post.id})">삭제</button>
                        </div>
                    </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>

<script>
    
    function deleteById(id) {
        $.ajax({
            type: "delete",
            url: "/company/deletePost/" + id,
            dataType: "json"
        })
        .done(res => { //20X 일때
            alert(res.msg);
            location.reload();
        })
        .fail(err => { //40X , 50X 일때
            console.log(err.responseJSON);
            alert(err.responseJSON.msg);
        });
    }
</script>

    <%@ include file="../layout/footer.jsp" %>