

const userName = $("#userName").val();
const proNum = $("#proNum").val();
console.log(userName);
//-------------------------------------------------
//디테일 페이지에서 신청 눌렀을 때
$("#ProAlretBtn").click(function(){
	const mpVO = {
		userName : userName,
		proNum : proNum
	}
	
	console.log(mpVO);
	
	$.ajax({
		type : "POST",
		url : "./add",
		data:JSON.stringify(mpVO),
		contentType: "application/json; charset=utf-8",
		dataType: "json",
		success:function(data){
			switch (data){
				case 2:
					alert("해당 프로그램을 이미 신청한 상태입니다.")
					break;
				case 1:
					let done = window.confirm("행사 신청이 완료되었습니다.\n마이페이지에서 확인하시겠습니까?");
					if(done){
						location.href="/mypage/program";
					} else{
                        location.href="../";
                        location.reload();
                    } 
				}
			},
			error:function(){
				console.log("ERROR");
			}
		})
	});
	
//-------------------------------------------------
//디테일 페이지에서 신청취소 눌렀을 때
$(".ProDelAlretBtn").click(function(){
	const mpVO = {
		userName : userName,
		proNum : $(this).attr("data-res-num")
	}
	
	console.log(mpVO);
	
	$.ajax({
		type : "POST",
		url : "/program/delete",
		data:JSON.stringify(mpVO),
		contentType: "application/json; charset=utf-8",
		dataType: "json",
		success:function(data){
			switch (data){
				case 1:
					let done = window.confirm("행사 신청이 취소되었습니다.");
					if(done){
						location.href="../";
						location.reload();
					} else{
                        location.href="../";
                        location.reload();
                    } 
				}
			},
			error:function(){
				console.log("ERROR");
			}
		})
	});	