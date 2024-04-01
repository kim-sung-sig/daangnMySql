$(function(){
	// GPT 개사기
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

    async function populateSelects() {
        // 첫 번째 select 요소 (시)
        const regionSelect = $('#r1');
        const regionData = await fetchRegionData();
        regionData.forEach(region => {
            regionSelect.append(`<option value="${region}">${region}</option>`);
        });
        const guSelect = $('#r2');
        const dongSelect = $('#r3');

        const resetSelects = () => {
            guSelect.empty().append('<option value="0">구을 선택하세요.</option>');
            dongSelect.empty().append('<option value="0">동을 선택하세요.</option>');
        };

        regionSelect.on('change', async function() {
            resetSelects(); // r1이 변경되면 r2, r3 초기화
            const selectedRegion = $(this).val();
            const regionData = await fetchRegionData(selectedRegion);
            regionData.forEach(region => {
                guSelect.append(`<option value="${region}">${region}</option>`);
            });
        });

        guSelect.on('change', async function() {
            dongSelect.empty().append('<option value="0">동을 선택하세요.</option>'); // r2가 변경되면 r3 초기화
            const selectedRegion = regionSelect.val();
            const selectedGu = $(this).val();
            const guData = await fetchRegionData(selectedRegion, selectedGu);
            guData.forEach(gu => {
                dongSelect.append(`<option value="${gu}">${gu}</option>`);
            });
        });
    }

    populateSelects();
})