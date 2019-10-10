#!/usr/local/bin/php -d display_errors=STDOUT
<?php
    date_default_timezone_set('America/Los_Angeles');
    $currentTime = time();
    $hours_to_show = 12;
    
    if (isset($_GET["pageSelection"]))
    {
        if($_GET["pageSelection"] != "Today") {
            $currentTime = $_GET["time_stamp"];
        }
    }
    
    function get_hour_string($timestamp)
    {
        $hour = date("g", $timestamp);
        $amPm = date("A", $timestamp);
        return $hour.":00".$amPm;
    }
    
    print'<?xml version = "1.0" encoding="utf-8"?>';
?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Calendar2</title>

<link rel="stylesheet" type="text/css" href="AdvancedCalendar.css"/>

<script type="text/javascript">
<!--

function init() {
    document.getElementById("sylviasbutton").click();
}

function deleted(node) {
    
    var sib = node.nextSibling;
    var nextsib = sib.nextSibling;
    var title = nextsib.innerHTML;
    
    if (confirm("Are you sure you want to delete this event?")) {
        process_form(false,true,title);
    }
}


function openForm(node) {
    
    var helentab = document.getElementById("helen_tab").style.display;
    var sylviatab = document.getElementById("sylvia_tab").style.display;
    var winonatab = document.getElementById("winona_tab").style.display;
    
    
    if (helentab == "block") {
        document.getElementById("helen").checked = true;
        document.getElementById("sylvia").checked = false;
        document.getElementById("winona").checked = false;
    }
    else if (sylviatab == "block") {
        document.getElementById("sylvia").checked = true;
        document.getElementById("helen").checked = false;
        document.getElementById("winona").checked = false;
    }
    else {
        document.getElementById("sylvia").checked = false;
        document.getElementById("helen").checked = false;
        document.getElementById("winona").checked = true;
    }
    
    
    document.getElementById('eventtitle').value= "";
    document.getElementById('eventmessage').value= "";
    document.getElementById('task').checked = false;
    
    var sibling = node.previousSibling.previousSibling;
    time = sibling.innerHTML;
    
    var shour = time.match(/(\d{1,2}):/)[1];
    var hour = parseInt(shour);
    
    var min = time.match(/:(\d\d)/)[1];
    
    var ampm = time.match(/[AMP]{2}/)[0];
    
    if (ampm == "PM" && hour!= 12) {
        hour = hour + 12;
    }
    
    if (ampm == "AM" && hour == 12) {
        hour = 0;
    }
    
    var timestart = document.getElementById('starttime');
    timestart.value = hour+":"+min;
    
    var headers = document.getElementById('headings');
    var words = headers.innerHTML;
    var month = words.match(/,\s(\D+)\s\d+,/)[1];
    var day = words.match(/,\s\D+\s(\d+),/)[1];
    var year = words.match(/\s\d+,\s(\d{4})/)[1];
    
    if (month == "January") {month = 1;}
    if (month == "February") {month = 2;}
    if (month == "March") {month = 3;}
    if (month == "April") {month = 4;}
    if (month == "May") {month = 5;}
    if (month == "June") {month = 6;}
    if (month == "July") {month = 7;}
    if (month == "August") {month = 8;}
    if (month == "September") {month = 9;}
    if (month == "October") {month = 10;}
    if (month == "November") {month = 11;}
    if (month == "December") {month = 12;}
    
    var datestart = document.getElementById('startdate');
    datestart.value = month+"/"+day+"/"+year;
    
    var form = document.getElementById('myinfoForm');
    form.style.display = "block";
    
}


function closeForm() {
    var form = document.getElementById('myinfoForm');
    form.style.display = "none";
}

function forceClose() {
    closeForm();
}

function process_form(justOpen, eventdeletion, deletiontitle) {
    
    var query_string = "";
    
    var currentTime = <?php echo $currentTime ?>;
    query_string = query_string+"currenttime="+currentTime;
    
    var helentab = document.getElementById("helen_tab").style.display;
    var sylviatab = document.getElementById("sylvia_tab").style.display;
    var winonatab = document.getElementById("winona_tab").style.display;
    
    
    if (helentab == "block") {
        var tabopen = "helen_tab";
        query_string = query_string+"&displaying=helen";
    }
    else if (sylviatab == "block") {
        var tabopen = "sylvia_tab";
        query_string = query_string+"&displaying=sylvia";
    }
    else {
        var tabopen = "winona_tab";
        query_string = query_string+"&displaying=winona";
    }
    
    
    if (justOpen == true) {
        query_string = query_string+"&justopen=yes";
        do_ajax_stuff(query_string, tabopen);
    }
    
    else if(eventdeletion == true) {
        query_string = query_string+"&justopen=no";
        query_string = query_string+"&eventdeletion=yes";
        query_string = query_string + "&deletiontitle="+deletiontitle;
        do_ajax_stuff(query_string, tabopen);
    }
    
    else {
        query_string = query_string+"&justopen=no&eventdeletion=no&deletiontitle=no";
        
        var form = document.getElementById('myinfoForm');
        form.style.display = "none";
        
        var starttime  = document.getElementById("starttime");
        var startdate  = document.getElementById("startdate");
        var repetition = document.getElementById("repetition");
        
        
        var eventmessage  = document.getElementById("eventmessage");
        var task  = document.getElementById("task");
        var eventtitle  = document.getElementById("eventtitle");
        
        var helen  = document.getElementById("helen");
        var sylvia = document.getElementById("sylvia");
        var winona = document.getElementById("winona");
        
        if (helen.checked == false && sylvia.checked == false && winona.checked == false) {
            alert("Please Choose At Least One Person!");
        }
        
        else if (starttime.value == "" || eventtitle == "" ) {
            alert("Please Enter All Time And Date Settings!");
        }
        
        else {
            
            if(helen.checked == true){
                query_string = query_string+"&helen=yes";
            }
            else {
                query_string = query_string+"&helen=no";
            }
            
            if(sylvia.checked == true){
                query_string = query_string+"&sylvia=yes";
            }
            else {
                query_string = query_string+"&sylvia=no";
            }
            
            
            if(winona.checked == true){
                query_string = query_string+"&winona=yes";
            }
            else {
                query_string = query_string+"&winona=no";
            }
            
            query_string = query_string+"&eventmessage="+eventmessage.value+"&starttime="+starttime.value+"&eventtitle="+eventtitle.value + "&startdate="+startdate.value+"&repetition="+repetition.value;
            
            if(task.checked == true){
                query_string = query_string+"&task=yes";
            }
            else {
                query_string = query_string+"&task=no";
            }
            alert("query sting is: " + query_string);
            do_ajax_stuff(query_string, tabopen);
        }
    }
}


function do_ajax_stuff(query_string, tabopen)
{
    var xhr = new XMLHttpRequest();
    
    xhr.onreadystatechange = function ()
    {
        if (xhr.readyState == 4 && xhr.status == 200)
        {
            var result = xhr.responseText;
            display_result(result, tabopen);
        }
    }
    
    
    xhr.open("GET", "http://pic.ucla.edu/~suoyiy/final_project/UpdateCalendarDatabase.php?" + query_string, true);
    xhr.send(null);
}


function display_result(result, tabopen)
{
    
    
    var Div = document.getElementById(tabopen);
    
    Div.innerHTML = result;
}

function openCalendar(event, name) {
    var tabbody = document.getElementsByClassName("tabbody");
    var person_tab = document.getElementsByClassName("person_tab");
    
    for (var i = 0; i < tabbody.length; i++) {
        tabbody[i].style.display = "none";
    }
    
    for (var i = 0; i < person_tab.length; i++) {
        person_tab[i].className = person_tab[i].className.replace("active", "");
    }
    
    document.getElementById(name).style.display = "block";
    event.currentTarget.className += " active";
    
    process_form(true, false, "");
}


//-->
</script>

</head>
<body onload="init()">

<div id="myinfoForm" class="infoForm">
<div class="form-content">
<div class="form-header">
<span class="close" onclick="closeForm()">&times;</span>
<h2>Create an Event</h2>
</div>
<div class="form-body">
<form action="#" method = "get" class="inputs">
<p class="column">
<br/>
<input type = "checkbox" id = "helen" name = "person" value = "Helen"/>Helen
<input type = "checkbox" id = "sylvia" name = "person" value = "Sylvia"/>Sylvia
<input type = "checkbox" id = "winona" name = "person" value = "Winona"/>Winona

<br/><br/>

Date (MM/DD/YYYY): <br/>
<input type="text" id = "startdate" name ="startdate" />

<br/><br/>

Time (HH:MM Military Time): <br/>
<input type="text" id="starttime" />

<br/><br/>

&nbsp; &nbsp; <input type="button" value="Create Event" onclick="process_form(false, false, '' )"/>
</p>


<p class="column">


Event Title: <br/>
<input type = "text" id = "eventtitle" name = "eventtitle"/>

<br/><br/>

Event Message: <br/>
<input type = "text" id="eventmessage" name = "eventmessage"/>

<br/><br/>

<input type = "checkbox" id = "task" name = "task" value = "istask"/> Create Event as a Task

<br/><br/>

Repeat Every (10 times): <br/>
<input type = "text" id = "repetition" name = "eventrepetition"/> Days

</p>

</form>
</div>

</div>
</div>



<div class="calendar_tab">
<button class="person_tab" onclick="openCalendar(event, 'helen_tab')">Helen</button>
<button class="person_tab" id = "sylviasbutton" onclick="openCalendar(event, 'sylvia_tab')">Sylvia</button>
<button class="person_tab" onclick="openCalendar(event, 'winona_tab')">Winona</button>
</div>

<div id="helen_tab" class="tabbody">

</div>

<div id="sylvia_tab" class="tabbody">

</div>

<div id="winona_tab" class="tabbody">

</div>



<p id="validations">
<a href="http://validator.w3.org/check?uri=referer"><img
src="http://www.w3.org/Icons/valid-xhtml11" alt="Valid XHTML 1.1" height="31" width="88" />
</a>

<a href="http://jigsaw.w3.org/css-validator/check/referer"> <img style="border:0;width:88px;height:31px"
src="http://jigsaw.w3.org/css-validator/images/vcss"
alt="Valid CSS!" />
</a>
</p>
</body>
</html>


