console.log("자바스크립트 연결");
$(document).on('click', '.lockId', function(e){
    e.preventDefault();
    let userName = $(this).attr("data-user-id");
    // 확인창
    let check = window.confirm("해당 계정을 비활성화 하시겠습니까?");
    if(check){

        // 계정 비활성화 컬럼 0으로 업데이트
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
    // 확인창
    let check = window.confirm("해당 계정을 활성화 하시겠습니까?");
    if(check){

        // 계정 활성화 컬럼 1로 업데이트
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