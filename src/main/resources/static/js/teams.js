const url="http://localhost:8081/user/team";

function generateTeamRows(teams){
    
    const teamsTableBody=document.querySelector("#teams-table-body");
    let html="";
    teams.forEach(team => {
        html+=`<tr>
                    <th>${team.teamId}</th>
                    <th>${team.teamName} </th>
                    <th>${team.totalScore} </th>
                    <th>${team.matchesPlayed} </th>
                    <th>${team.wins} </th>
                    <th>${team.draws} </th>
                </tr>`;
    });

    teamsTableBody.innerHTML=html;
}
async function init() { 
    const teams = await fetchData(url,false);
    generateTeamRows(teams);
}   

init();

const addTeamBtn=document.querySelector("#add-team");
const removeTeamBtn=document.querySelector("#remove-team");
const renameTeamBtn=document.querySelector("#rename-team");
const assignPlayerBtn=document.querySelector("#assign-player-to-team");

const addModal=document.querySelector("#add-team-modal");
const removeModal=document.querySelector("#remove-team-modal");
const renameModal=document.querySelector("#rename-team-modal");
const assignPlayerModal=document.querySelector("#assign-player-to-team-modal");

addTeamBtn.addEventListener("click",()=>{
    addModal.style.display="flex";
    removeModal.style.display="none";
    renameModal.style.display="none";
    assignPlayerModal.style.display="none";
})
removeTeamBtn.addEventListener("click",()=>{
    addModal.style.display="none";
    removeModal.style.display="flex";
    renameModal.style.display="none";
    assignPlayerModal.style.display="none";
})
renameTeamBtn.addEventListener("click",()=>{
    addModal.style.display="none";
    removeModal.style.display="none";
    renameModal.style.display="flex";
    assignPlayerModal.style.display="none";
})
assignPlayerBtn.addEventListener("click",()=>{
    addModal.style.display="none";
    removeModal.style.display="none";
    renameModal.style.display="none";
    assignPlayerModal.style.display="flex";
})

document.querySelectorAll(".modal-cancel").forEach((btn) => {
    btn.addEventListener("click", () => {
        btn.closest(".modal").style.display = "none";
    });
});

