async function showMatches() {
    const response= await fetch("http://localhost:8081/user/match",{
        method:"GET",
        headers:{
            "authorization":`Bearer ${localStorage.getItem("accessToken")}`
        }
    });

    if(!response.ok){
        console.log(response.status);
        return;
    }
    const data=await response.json();
    return data;
}

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
    const matches = await showMatches();
    generateMatchRows(matches);
}   

init();


const addMatchBtn=document.querySelector("#add-match");
const addMatchModal=document.querySelector("#add-match-modal");

addMatchBtn.addEventListener("click",()=>{
    addMatchModal.style.display="flex";
})

const cancelAddMatchBtn=document.querySelector("#add-match-cancel");
cancelAddMatchBtn.addEventListener("click",()=>{
    addMatchModal.style.display="none";
})

const matchConsoleBtn=document.querySelector("#match-console");
matchConsoleBtn.addEventListener("click",()=>{
    window.location.href="matchAdminConsole.html";
})