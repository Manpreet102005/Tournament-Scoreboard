
async function fetchData(url,paginated=false,page=0,size=10) {
    const fullUrl=paginated?`${url}?page=${page}&size=${size}`:url;
    const response= await fetch(fullUrl,{
        method:"GET",
        headers:{
            "authorization":`Bearer ${localStorage.getItem("accessToken")}`
        }
    });

    if(!response.ok){
        console.log(response.status);
        return paginated ? { content: [], totalPages: 0 } : [];
    }
    const data=await response.json();
    console.log(data);
    return data;
}

function handlePagination(data,page,nextBtn,prevBtn){
    if(page>=data.totalPages-1){
        nextBtn.disabled=true;
        nextBtn.style.backgroundColor="grey";
    }
    else{
        nextBtn.disabled=false;
        nextBtn.style.backgroundColor="#1d57a1";

    }
    if(page===0){
        prevBtn.disabled=true;
        prevBtn.style.backgroundColor="grey";
    }
    else{
        prevBtn.disabled=false; 
        prevBtn.style.backgroundColor="#1d57a1";
    }
}