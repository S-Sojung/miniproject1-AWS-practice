<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ include file="../layout/header.jsp" %>
 <div class="container d-flex">
        <div class="list-group mx-2">
            <a href="/company/info" class="list-group-item shadow" style="width: 150px;">회사 정보</a>
            <a href="/company/posts" class="list-group-item hs_list_effect shadow">공고 관리</a>
            <a href="/company/getResume" class="list-group-item shadow">받은 이력서</a>
            <a href="/company/scrap" class="list-group-item  shadow">스크랩한 유저</a>
        </div>

        <div class=" mx-2 pb-4 w-100">
            <div class="border border-tertiary  p-5 rounded shadow">
                <h1 class="mb-4">등록한 공고</h1>
                <hr>
                <div class="jh_resume mt-5">
                    <button class="jh_resume_button mb-5 rounded  jm_card" onclick="location.href=`/company/savePostForm`;">➕ 새로운 공고
                        등록</button>
                    
                    <c:forEach items="${postTitleList}" var="post" varStatus="status">
                    <div class="jh_resume_content mb-3" style="display: flex; justify-content: space-between">
                    <div style="">
                        <a href="/company/postDetail/${post.id}"  style="text-decoration: none;">${post.title}</a>
                        <div id="post_deadline_${status.count}" value="${post.deadline}">마감 기한 : ~${post.deadline}</div>
                    </div>
                    <div style="display: flex;">
                        <button type="button" id="delete_button_${status.count}" class="btn btn-dark" onclick="deleteById(${post.id})">삭제</button>
                        </div>
                    </div>
                    </c:forEach>
                     <input type="hidden" value="${size}" id="postSize"/>
                </div>
            </div>
        </div>
    </div>

<script>
    let postSize = document.querySelector("#postSize").getAttribute("value");
    let now = new Date(); // Timestamp
    
    for (let i =1; i <= postSize; i++) {     
        let postDate = $("#post_deadline_"+i).get(0).getAttribute("value");
        let postDateNew = new Date(postDate); //String -> Timestamp
        if (postDateNew <= now) {
            $("#delete_button_"+i).css("background-color","red");
            $("#delete_button_"+i).css("border-color","red");
        }
    }

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