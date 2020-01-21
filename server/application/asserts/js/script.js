
 var message = document.getElementById('message');
 var basic_message = "Status: ";
  setInterval(function() {
    var xmlhttp = new XMLHttpRequest();
         xmlhttp.onreadystatechange = function() {

             if (this.readyState == 4 && this.status == 200) {
                Get_Result(JSON.parse(this.responseText));
             }
             else {
               message.innerHTML=basic_message+"Getting ....";
             }
         };
         xmlhttp.open("GET", "http://"+ServerUrl+"/server/index.php/welcome/getpcs", true);
         xmlhttp.send();

  }, 1000 * 5);
const Cost = 0.5;    // per half hour
const time =  60; /// second


function EditPrice(myobj,entry)
{
  var total = parseInt(myobj.price/time)*Cost;
  if(total == 0)
      total = 0.5;
  entry.innerHTML = "€&nbsp"+total.toFixed(2);
}
function EditTime(myobj,entry)
{
  if(myobj.status == "Online unlock")
     {
       var total = myobj.price;

       var sec = parseInt(total % 60);
       sec = MakeTwoDigit(sec);
       var min = parseInt((total % 3600)/60);
       min = MakeTwoDigit(min);
       var hou = parseInt(total /3600);
       hou = MakeTwoDigit(hou);
       entry.innerHTML = hou+":"+min+":"+sec;
     }
}
function EditHidden(myobj,entry,i)
{
  if(myobj.status.includes('Online'))
    {
      entry.style.display = "block";

      if(!myobj.status.includes('unlock'))
          OnlineOfflinebtn[i].style.display = "none";
      else
          OnlineOfflinebtn[i].style.display = "block";
    }
   else {
     entry.style.display = "none";
   }
}
function Get_Result(myobj)
{
  var i = 0;
  statusPc.forEach(function(entry) {
    EditPrice(myobj[i],pricePc[i]);
    EditTime(myobj[i],timerunning[i]);
    EditHidden(myobj[i],OnlineOffline[i],i);
     entry.innerHTML = myobj[i].status;
     i++;
   });

   message.innerHTML=basic_message+"";
}

function MakeTwoDigit(n){
    return n > 9 ? "" + n: "0" + n;
}

const lock='lock',unlock='unlock',shutdownStatus='shutdown';
function setExcute(id, type)
{
  if(statusPc[id-1].innerHTML == "Offline")
    {alert("The User Is Offline");
    return null;}
  var xmlhttp = new XMLHttpRequest();
  xmlhttp.open("GET", "http://"+ServerUrl+"/server/index.php/welcome/setExcute/"+id+"/"+type  , true);
  xmlhttp.send();
}
