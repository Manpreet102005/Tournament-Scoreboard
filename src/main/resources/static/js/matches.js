const url="http://localhost:8081/user/match";
let page=0;

const nextBtn=document.querySelector("#next");
const prevBtn=document.querySelector("#prev");

function generateMatchRows(matches){
    
    const matchesTableBody=document.querySelector("#matches-table-body");
    let html="";
    matches.forEach(match => {
        html+=`<tr>
                    <th>${match.matchId}</th>
                    <th>${match.matchTitle} </th>
                    <th>${match.matchDateTime} </th>
                    <th>${match.teamAName} </th>
                    <th>${match.teamBName} </th>
                    <th>${match.teamAScore} </th>
                    <th>${match.teamBScore} </th>
                    <th>${match.matchStatus} </th>
                </tr>`;
    });

    matchesTableBody.innerHTML=html;
}
async function init() { 
    const matches = await fetchData(url,true,page);
    generateMatchRows(matches.content);
    handlePagination(matches,page,nextBtn,prevBtn);
}   

init();


const addMatchBtn=document.querySelector("#add-match");
const addMatchModal=document.querySelector("#add-match-modal");

addMatchBtn.addEventListener("click",()=>{
    addMatchModal.style.display="flex";
})
const confirmAddMatchBtn= document.querySelector("#add-match-ok");
confirmAddMatchBtn.addEventListener("click",async ()=>{
    const matchTitle=document.querySelector("#match-title").value;
    const matchDateTime=document.querySelector("#match-date-time").value;    
    const teamAId=document.querySelector("#team-A-id").value;
    const teamBId=document.querySelector("#team-B-id").value;   
    const response= await fetch(`http://localhost:8081/admin/match/${teamAId}/${teamBId}`,{
        method:"POST",
        headers:{
            "content-type":"application/json",
            "authorization":`Bearer ${localStorage.getItem("accessToken")}`
        },
        body:JSON.stringify(matchTitle,matchDateTime)
    });
    const data=await response.text();
    console.log(data);
    alert(data);
    if(response.ok){
        document.querySelector("#add-match-modal").style.display="none";
    }
})

const cancelAddMatchBtn=document.querySelector("#add-match-cancel");
cancelAddMatchBtn.addEventListener("click",()=>{
    addMatchModal.style.display="none";
})

const matchConsoleBtn=document.querySelector("#match-console");
matchConsoleBtn.addEventListener("click",()=>{
    window.location.href="matchAdminConsole.html";
})