$(function(){
	const getRegion = $("#region").val();
	const getGu = $("#gu").val();
	const getDong = $("#dong").val();
	const regionSelect = $('#r1');
	const guSelect = $('#r2');
	const dongSelect = $('#r3');
	const categoryRef = $("#categoryRef").val();
	const search = $("#search").val();
	
	let query = "";
	if(categoryRef.length != 0 && search.length != 0){
		query += `?categoryRef=${categoryRef}&search=${search}`;
	} else if(categoryRef != 0){
		query += `?categoryRef=${categoryRef}`;
	} else if(search.length != 0){
		query += `?search=${search}`
	}
	
	populateSelects(regionSelect, getRegion, '', '', '');
	
	regionSelect.on('change', function(){
		const selectedRegion = regionSelect.val();
		if(selectedRegion == '0'){
			location.href = `/life/view` + query;					
		} else {
			location.href = `/life/view/${selectedRegion}` + query;					
		}
	});
	
	if(getRegion.length != 0){
		console.log('asf');
		populateSelects(guSelect, getGu, getRegion, '', '');
		
		guSelect.on('change', function(){
			const selectedGu = guSelect.val();
			if(selectedGu == '0'){
				location.href = `/life/view/${getRegion}` + query;					
			} else {
				location.href = `/life/view/${getRegion}/${selectedGu}` + query;					
			}
		})
	}
	
	if(getGu.length != 0){
		populateSelects(dongSelect, getDong, getRegion, getGu, '');
		dongSelect.on('change', function(){
			const selectedDong = dongSelect.val();
			if(selectedDong == '0'){
				location.href = `/life/view/${getRegion}/${getGu}` + query;					
			} else {
				location.href = `/life/view/${getRegion}/${getGu}/${selectedDong}` + query;					
			}
		})
	}
	
	
	async function fetchRegionData(region, gu, dong) {
        try {
            const response = await axios.get('/region', {
                params: {
                    'region': region,
                    'gu': gu,
                    'dong': dong
                }
            });
            return response.data;
        } catch (error) {
            console.error('Error fetching region data:', error);
        }
    }
	
	async function populateSelects(obj, selected, region, gu, dong) {
        // 첫 번째 select 요소 (시)
        const select = obj;
		let data;
        if(region.length == 0){
	        data = await fetchRegionData();
        } else if (gu.length == 0){
        	data = await fetchRegionData(region);
        } else {
        	data = await fetchRegionData(region, gu);
        }
        data.forEach(value => {
        	if(value == selected){
        		select.append(`<option value="${value}" selected>${value}</option>`);		        		
        	} else {
        		select.append(`<option value="${value}">${value}</option>`);		        				        		
        	}
        });
    }
    
    $("#searchForm").submit(function(){
		const searchText = $("#searchText").val();
		if(searchText.trim().length==0){
			alert('검색어를 입력해주세요.');
			$("#searchText").val('');
			$("#searchText").focus();			
		}
		if(categoryRef.length != 0){
			location.href= `?categoryRef=${categoryRef}&search=${searchText}`;
		} else {
			location.href= `?search=${searchText}`;
		}
		return false;
	})
})