console.log("자바스크립트 연결");
// function memberLocked(num){
//     let userName = num;
//     console.log("userName : ", userName);
//     // let check = window.confirm("해당 계정을 비활성화 하시겠습니까?");
//     // if(check){
//     //     $("#lockedForm").submit();
//     //     console.log("확인");
//     // }else{
//     //     console.log("취소");
//     //     return;
//     // }
// };

// $(".userList").on("click", ".userId", function(){
//     console.log("클릭이벤트");
//     let userName = $(this).attr("data-member-id");
//     console.log("클릭한 멤버 아이디 : ", userName);
// });

// $(".userId").click(function(){

//     console.log("클릭이벤트");
// });

$(document).on('click', '.lockId', function(e){
    e.preventDefault();
    let userName = $(this).attr("data-user-id");
    console.log("유저아이디 : ", userName);
    let check = window.confirm("해당 계정을 비활성화 하시겠습니까?");
    if(check){

        console.log("확인");

        $.ajax({
            type:"POST",
            url:"locked",
            data:{
                userName:userName
            },
            success:function(result){
                if(result > 0){
                    alert("계정 비활성화가 되었습니다.");
                    location.reload();
                }else{
                    alert("계정 비활성화를 실패했습니다.");
                }
            }
        });

    }else{

        console.log("취소");
        return;

    }

});

$(document).on('click', '.unLockId', function(e){
    e.preventDefault();
    let userName = $(this).attr("data-user-id");
    console.log("계정활성화 유저아이디 : ", userName);
    let check = window.confirm("해당 계정을 활성화 하시겠습니까?");
    if(check){

        console.log("확인");

        $.ajax({
            type:"POST",
            url:"unLocked",
            data:{
                userName:userName
            },
            success:function(result){
                if(result > 0){
                    alert("계정 활성화가 되었습니다.");
                    location.reload();
                }else{
                    alert("계정 활성화를 실패했습니다.");
                }
            }
        });

    }else{

        console.log("취소");
        return;

    }

});