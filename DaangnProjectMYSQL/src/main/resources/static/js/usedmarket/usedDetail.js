function detail(idx){
    window.open('/used-market/detail/' + idx, '_blank');
}

function initializeMap() {
	let latitude = $("#latitude").val();
	let longitude = $("#longitude").val();
    console.log("지도 초기화 및 기타 작업 실행");
    
    // 카카오 지도 뿌리기
	let mapContainer = document.getElementById('map'); 
	let mapOption = { 
        center: new kakao.maps.LatLng(latitude, longitude),
        level: 3 // 지도의 확대 레벨
    };
	var map = new kakao.maps.Map(mapContainer, mapOption);
	
	var markerPosition  = new kakao.maps.LatLng(latitude, longitude); 
	
	var marker = new kakao.maps.Marker({
	    position: markerPosition
	});
	marker.setMap(map);
}

/** 날짜변경 */
function dateFormatter(dateStr){
	// Moment.js를 사용하여 상대적인 시간 표시 
	let momentRegDate = moment(dateStr);
	let now = moment();
	let diff = now.diff(momentRegDate, 'seconds'); // 차이 구하기
	var formattedDate;
	if (diff < 60) {
	    formattedDate = diff + '초 전';
	} else if (diff < 3600) {
	    formattedDate = Math.floor(diff / 60) + '분 전';
	} else if (diff < 86400) {
	    formattedDate = Math.floor(diff / 3600) + '시간 전';
	} else if (diff < 604800) {
	    formattedDate = Math.floor(diff / 86400) + '일 전';
	} else {
	    formattedDate = momentRegDate.format('YYYY.MM.DD');
	}
	return formattedDate;
}


$(function(){
	
	let boardTime = dateFormatter($("#board-time2").val());
	console.log(boardTime);
	$("#board-time").text(boardTime);
	
	initializeMap();
	
	// let statusNum = $("#boardStatusNum").val();
	
	// 카카오 지도 뿌리기
	let mapContainer = document.getElementById('map'); 
	let mapOption = { 
        center: new kakao.maps.LatLng($("#latitude").val(), $("#longitude").val()),
        level: 3 // 지도의 확대 레벨
    };
	var map = new kakao.maps.Map(mapContainer, mapOption);
	
	var markerPosition  = new kakao.maps.LatLng($("#latitude").val(), $("#longitude").val()); 
	
	var marker = new kakao.maps.Marker({
	    position: markerPosition
	});
	marker.setMap(map);
	
	
	
	
	
	// 좋아요 누르기!
	$("#likeHeart > a").click(function(e){
		e.preventDefault();
		if($("#likeHeart > a").hasClass("active")){
			axios.delete("/api/like",{
				data: {
			        boardIdx: $("#boardIdx").val()
			    }
			})
			.then(function (res) {
				data = res.data;
				if(data==1){
					$("#likeHeart > a").removeClass("active");
					$("#countLike").text('관심 ' + (Number($("#countLike").text().substring(3)) - 1));
				} else {
					alert("오류가 발생했습니다. 잠시후에 다시 시도해주세요.")
				}
			})
			.catch(function (err) {
				console.log(err);
			});
			
		} else {
			axios.post("/api/like",{
				'boardIdx': $("#boardIdx").val()
			})
			.then(function (res) {
				data = res.data;
				if(data==1){
					$("#likeHeart > a").addClass("active");
					$("#countLike").text('관심 ' + (Number($("#countLike").text().substring(3)) + 1));
				} else {
					alert("오류가 발생했습니다. 잠시후에 다시 시도해주세요.")
				}
			})
			.catch(function (err) {
				console.log(err);
			});
		}
	})
	
	
	
	
	
	$("#chatBtn").submit(function(){
		axios.post("/chat/createChatRoom",{
			'boardIdx': $("#boardIdx").val()
		})
		.then(function (res) {
			data = res.data;
			console.log(data);
			if(data!=0){
				const url = `/chat/room/${data}`;
				const popupWidth = 400;
	            const popupHeight = 705;
	            const leftPosition = (window.screen.width - popupWidth) / 2;
	            const topPosition = (window.screen.height - popupHeight) / 2;
	            const popupWindow = window.open(url, "ChatRoomPopup", `width=${popupWidth}, height=${popupHeight}, left=${leftPosition}, top=${topPosition}, resizable=no`);
	            popupWindow.focus();
			} else {
				alert("로그인후 이용가능합니다.")
			}
		})
		.catch(function (err) {
			console.log(err);
		});
		
		return false;
	})
	
	$("#updateBtn").click(function(){
		const idx = $("#boardIdx").val();
		const form = $('<form>').attr({
		    action: '/fleamarketUpdate',
		    method: 'post'
		});
		const input = $('<input>').attr({
		    type: 'hidden',
		    name: 'idx',
		    value: idx
		});
		form.append(input);
		$('body').append(form);
		form.submit();
	})
	
	
	$("#statusChange").change(function(){
		const statusRef = $("#statusChange").val();
		const boardIdx = $("#boardIdx").val();
		const result = confirm('상품 상태를 변경하시겠습니까?');
		if(result) {
			const form = $('<form>');
		    form.attr('method', 'post');
		    form.attr('action', `/fleamarketStatusUpdate`);
		    form.append($('<input>').attr('type', 'hidden').attr('name', 'boardIdx').val(boardIdx));
		    form.append($('<input>').attr('type', 'hidden').attr('name', 'statusRef').val(statusRef));
		    $('body').append(form);
		    form.submit();
		}
	})
	
	
	let uv = $("#userVal").val();
	let score = 6.5 * (uv - 3) + 36.5;
 	console.log(score);
	score = score.toFixed(1);
	$("#userValBox").html(`
		<div><span style="font-weight:400;">매너온도</span> ${score} °C</div>
			<div style="position: relative; width: 100%; height: 5px; border-radius: 3px; background: #eee; overflow: hidden;">
		    <div style="position: absolute;top: 0; left: 0; width: ${uv/6 *100}%; height: 100%; background: #FF8A3D;"></div>
		</div>`
	);
	
})


