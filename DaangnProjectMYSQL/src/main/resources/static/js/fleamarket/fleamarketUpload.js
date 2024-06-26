const defaultLatitude = 37.5665;
const defaultLongitude = 126.9780;

let latitude = 37.5665;
let longitude = 126.9780;

function mapInit(){
	
	// 현재 위치의 경도 위도를 가져오는함수
	navigator.geolocation.getCurrentPosition(
		(position) => { // 가져온경우
	        latitude = position.coords.latitude;
	        longitude = position.coords.longitude;
	        // 나머지 코드
			console.log('가져옴!');
			console.log(latitude, longitude);
			initializeMap();
	    },
	    (error) => { // 못가져온경우
	        // 위치를 가져오지 못한 경우에 기본값 사용
	        latitude = defaultLatitude;
	        longitude = defaultLongitude;
	        // 나머지 코드
			console.log('못가져옴!');
			console.log(latitude, longitude);
			initializeMap();
	    }
	);
}

// 위치 정보를 기반으로 지도 초기화 및 기타 작업을 실행하는 함수
function initializeMap() {
    console.log("지도 초기화 및 기타 작업 실행");
    console.log("현재 위도:", latitude);
    console.log("현재 경도:", longitude);
    
    let mapContainer = document.getElementById('map');
    let mapOption = {
        center: new kakao.maps.LatLng(latitude, longitude), // 지도의 중심좌표
        level: 3 // 지도의 확대 레벨
    };
    var map = new kakao.maps.Map(mapContainer, mapOption);

    // 컨트롤러 설정!
    var mapTypeControl = new kakao.maps.MapTypeControl();
    map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);
    var zoomControl = new kakao.maps.ZoomControl();
    map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);

    // 주소-좌표 변환 객체를 생성합니다
    var geocoder = new kakao.maps.services.Geocoder();

    // 마커를 생성합니다
    function addMarker(position) {
        var marker = new kakao.maps.Marker({
            position: position
        });
        // 마커가 지도 위에 표시되도록 설정합니다
        marker.setMap(map);
        // 생성된 마커를 배열에 추가합니다
        markers.push(marker);
    }

    // 마커 모두 지우는 함수
    function setMarkers(map) {
        for (var i = 0; i < markers.length; i++) {
            markers[i].setMap(null);
        }
    }

    function searchAddrFromCoords(coords, callback) {
        // 좌표로 행정동 주소 정보를 요청합니다
        geocoder.coord2RegionCode(coords.getLng(), coords.getLat(), callback);
    }

    function inputText(result, status) {
        if (status === kakao.maps.services.Status.OK) {
            for (var i = 0; i < result.length; i++) {
                // 행정동의 region_type 값은 'H' 이므로
                if (result[i].region_type === 'H') {
                    let content =
                        `${result[i].address_name}에 올릴 게시글 내용을 작성해 주세요.
(판매 금지 물품은 게시가 제한될 수 있어요!)

신뢰할 수 있는 거래를 위해 자세히 적어주세요.
과학기술정보통신부, 한국 인터넷진흥원과 함께해요.`;
                    $("#content").attr("placeholder", content);
                    $("#loc").val(result[i].address_name);
                    break;
                }
            }
        }
    }

    // **초기화 영역**
    // 마커를 담은 배열
    var markers = [];
    // 마커 하나를 지도위에 표시합니다 
    addMarker(new kakao.maps.LatLng(latitude, longitude));

    searchAddrFromCoords(map.getCenter(), inputText)
    $("#latitude").val(latitude)
    $("#longitude").val(longitude)
    // 그후 지도가 움직일때 실행될 영역
    // 마우스 드래그로 지도 이동이 완료되었을 때 마지막 파라미터로 넘어온 함수를 호출하도록 이벤트를 등록합니다
    kakao.maps.event.addListener(map, 'center_changed', function() {
        // 마커 모두 지우기
        setMarkers(map)
        // 지도 중심좌표를 얻어옵니다 
        var latlng = map.getCenter();
        // 마커찍기
        addMarker(new kakao.maps.LatLng(latlng.getLat(), latlng.getLng()));
        searchAddrFromCoords(map.getCenter(), inputText)
        $("#latitude").val(latlng.getLat())
        $("#longitude").val(latlng.getLng())
    });
}


$(function() {
	// map 생성
	mapInit();


	// 1. 파일 선택 버튼 클릭시 파일 선택 창 열기
	$("#fileBtn").click(function () {
        $("#fileInput").click();
    });
    $("#fileInput").change(function(){
		const files = Array.from($(this)[0].files);
		insertFiles(files);
	})
	// 2. 드래그 앤 드롭 영역 설정
    const fileDropArea = document.getElementById('file-drop-area');
    
    // 3. dragover 이벤트 처리
    fileDropArea.addEventListener('dragover', function(e) {
        e.preventDefault();
        e.stopPropagation();
        fileDropArea.classList.add('highlight');
    });
    // 4. dragleave 이벤트 처리
    fileDropArea.addEventListener('dragleave', function(e) {
        e.preventDefault();
        e.stopPropagation();
        fileDropArea.classList.remove('highlight');
    });
    
    // 5. drop 이벤트 처리
    fileDropArea.addEventListener('drop', function(e) {
        e.preventDefault();
        fileDropArea.classList.remove('highlight');
        const files = e.dataTransfer.files;
        insertFiles(files);
    });
    /** 파일 최대크기에 도달햇는지 확인하는 함수 */
    function getfileCount(){
		let result = 0;
		let files = document.querySelectorAll(".inputFile");
		result = files.length;
		return result;
	}
	/** file이 사진인지 아닌지 판단하는 함수 */
	function isImage(file) {
	    return file.type.startsWith('image/');
	}
	/** fileBox에 inputfile을 넣어주고 미리보기를 만드는 함수 */
	function insertFiles(files){
		const fileBox = document.querySelector("#fileBox");
		const previewElement = document.querySelector('#preview');
		let currentFileCount = getfileCount();
        for (let i = 0; i < files.length; i++) {
            if(currentFileCount + i < 5){
	            let reader = new FileReader();
	            // 파일의 내용을 읽어 데이터 URL로 변환
	            reader.readAsDataURL(files[i]);
	            // 읽기가 완료된 후 실행되는 콜백 함수
	            reader.onload = function () {
					if(isImage(files[i])){
						const newFile = document.createElement('input');
			            newFile.setAttribute("type", "file");
			            newFile.setAttribute("name", "file");
			            newFile.setAttribute("class", "inputFile");
					
			            // 파일을 input에 추가
			            const fileList = new DataTransfer();
			            fileList.items.add(files[i]);
			            newFile.files = fileList.files;
			            fileBox.appendChild(newFile);
			            
		                // 미리보기 이미지를 생성하여 추가
		                let div = document.createElement('div');
		                div.classList.add("previewImgBox");
		                let img = document.createElement('img');
		                img.src = reader.result;
		                img.alt = 'Preview';
		                let span = document.createElement('span');
		                span.classList.add('imgCancleBtn')
		                span.setAttribute('uk-icon', 'icon: close');
		                span.addEventListener('click', function(){
							let obj = this;
							removeFile(obj);
						})
		                div.appendChild(img);
		                div.appendChild(span);
		                previewElement.appendChild(div);					
					} else {
						alert("image형식의 파일만 올릴 수 있습니다.");
					}
	            };
			} else {
				alert('최대 5장까지만 올릴 수 있습니다.');
				break;
			}
        };
	};
	
	/**
	 * span을 누른경우 미리보기 사진삭제및 file 삭제하기
	 */
	function removeFile(obj){
		const parentBox = obj.closest('.previewImgBox');
		const index = Array.from(parentBox.parentNode.children).indexOf(parentBox);
		const fileBox = document.querySelector("#fileBox");
		fileBox.removeChild(fileBox.children[index]);
		parentBox.remove();
	}
    
    // 폼체크
    $("#uploadForm").submit(function(){
		const fileBox = document.querySelector('#fileBox');
		const childCount = fileBox.children.length;
		if(childCount == 0){
			alert('파일을 등록해주세요.');
			$("#fileInput").focus();
			return false;
		}
		let value = $("#subject").val();
		if(value.trim().length == 0){
			alert('제목을 입력해주세요.');
			$("#subject").val("");
			$("#subject").focus();
			return false;
		}
		
		value = $("#price").val();
		const maxValue = 2147483647; // 자바의 최대정수
		if (parseInt(value) > maxValue) {
		    alert("입력범위보다 큰 값을 입력하셨습니다.");
		    $("#price").val('');
		    $("#price").focus();
		    return false;
		}
		
		value = $("#content").val();
		if(value.trim().length == 0){
			alert('내용을 입력해주세요.');
			$("#content").val("");
			$("#content").focus();
			return false;
		}
		
		value = $("#detailAddress").val();
		if(value.trim().length == 0){
			alert('상세주소를 입력해주세요.');
			$("#detailAddress").val("");
			$("#detailAddress").focus();
			return false;
		}
		
		if(value.search(/[\{\}\[\]\/?.,;:|\)*~`!^\-_+<>@\#$%&\\\=\(\'\"]/gi) >= 0){
			alert('상세주소에는 특수문자는 사용할 수 없습니다.');
			$("#detailAddress").val("");
			$("#detailAddress").focus();
			return false;
		}
		return true;
	});
	
	$("#homeBtn").click(function(){
		let result = confirm("물건팔기를 취소하시겠습니까?")
		if(result){
			location.href='/fleamarket';
		}
	})
	
	
	$("#content").on('input', function() {
	    var text = $("#content").val();
	    var maxLength = parseInt($("#content").attr('maxlength'));
	    var remaining = maxLength - text.length;

	    if (remaining >= 0) {
	        $("#charCount").text(`${text.length} / ${maxLength}`);
	    } else {
	        $("#content").val(text.substring(0, maxLength));
	        $("#charCount").text(`${maxLength} / ${maxLength}`);
	    }
	});
});