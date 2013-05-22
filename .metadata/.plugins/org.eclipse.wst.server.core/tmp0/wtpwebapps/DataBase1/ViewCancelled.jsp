 <%@page import="ModifyTable.ModifyTable"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="shortcut icon" href="favicon1.gif" type="image/x-icon" />  
<title>Cancelled Table</title>
<%@ page language="java" import="DataBase1.*" %>
<%@ page language="java" import="java.io.*"%>
<%@ page language="java" import="java.util.ArrayList"%><head>
<script type="text/javascript">
window.onload=function()
		{
		onloaded();
		};

function onloaded()
{
	
	var table = document.getElementById("newTable");
	table.hidden=true;
	openFilter();
	var init = document.getElementById("inithid");
	init.hidden = false;


	var head = document.getElementById("th1");
	head.hidden = true;
	head = document.getElementById("th3");
	head.hidden = true;
	head = document.getElementById("th4");
	head.hidden = true;
	head = document.getElementById("th5");
	head.hidden = true;
	head = document.getElementById("th6");
	head.hidden = true;
	head = document.getElementById("th15");
	head.hidden = true;
	head = document.getElementById("th16");
	head.hidden = true;
	
	var element = document.getElementById('Select1');
	element.checked=0;
	element = document.getElementById('Select3');
	element.checked=0;
	element = document.getElementById('Select4');
	element.checked=0;
	element = document.getElementById('Select5');
	element.checked=0;
	element = document.getElementById('Select6');
	element.checked=0;
	element = document.getElementById('Select15');
	element.checked=0;
	element = document.getElementById('Select16');
	element.checked=0;
	
	selected(1);
	selected(3);
	selected(4);
	selected(5);
	selected(6);
	selected(15);
	selected(16);
	
	
};

isBox=-1;
function openMes()
{
	isBox=0;
	var init = document.getElementById("inithid");
	var init1 = document.getElementById("init");
	init1.hidden=true;
	init.hidden=false;
	for(var i=0;i<37;i++)
	{	
	var string = 'Select';
	string = string.concat(i);
	var element = document.getElementById(string);
	element.checked=1;
	}
	var table = document.getElementById("newTable");
	var table1 =document.getElementById("fullTable");
	var tableBody = table.getElementsByTagName("tbody")[0]; 
	var table1Body = table1.getElementsByTagName("tbody")[0]; 
	var tableRows = tableBody.getElementsByTagName("tr");
	var newTableBody = document.createElement("tbody");
	for (var i=0; i < tableRows.length; i++)
	{ 
		var tableData = tableRows[i].getElementsByTagName("td");
		var data = tableData[11].firstChild.nodeValue;
		if(data.trim()=="MES Loose")
			newTableBody.appendChild(tableRows[i].cloneNode(true));
	}
	table1.replaceChild(newTableBody, table1Body);		
}


function openBox()
{
	isBox=1;
	var init = document.getElementById("inithid");
	var init1 = document.getElementById("init");
	init1.hidden=true;
	init.hidden=false;
	for(var i=0;i<37;i++)
	{	
	var string = 'Select';
	string = string.concat(i);
	var element = document.getElementById(string);
	element.checked=1;
	}
	var table = document.getElementById("newTable");
	var table1 =document.getElementById("fullTable");
	var tableBody = table.getElementsByTagName("tbody")[0]; 
	var table1Body = table1.getElementsByTagName("tbody")[0]; 
	var tableRows = tableBody.getElementsByTagName("tr");
	var newTableBody = document.createElement("tbody");
	for (var i=0; i < tableRows.length; i++)
	{ 
		var tableData = tableRows[i].getElementsByTagName("td");
		var data = tableData[11].firstChild.nodeValue;
		if(data.trim()!="MES Loose")
			newTableBody.appendChild(tableRows[i].cloneNode(true));
	}
	table1.replaceChild(newTableBody, table1Body);		
}


function search()
{
	var string = document.getElementById("searchBar").value;
	string= string.trim();
	string= string.toUpperCase();
	var table = document.getElementById("newTable");
	var table1 =document.getElementById("fullTable");
	var tableBody = table.getElementsByTagName("tbody")[0]; 
	var table1Body = table1.getElementsByTagName("tbody")[0]; 
	var tableRows = tableBody.getElementsByTagName("tr");
	var newTableBody = document.createElement("tbody");
	
	for(var i=0;i<1;i++)
	{
		var newTableRow = document.createElement("tr");
	var tableData = tableRows[i].getElementsByTagName("td");	

		for(var s=0;s<tableData.length;s++)
		{
			var isDel=-1;
			for(var t=0;t<prevdel.length;t++)
			{

			if(prevdel[t]==s)
				isDel++;
			}
		if(isDel==-1)
		newTableRow.appendChild(tableData[s].cloneNode(true));
		}
		newTableBody.appendChild(newTableRow.cloneNode(true));
		}

	

	for (var i=1; i < tableRows.length; i++)
	{ 
		var tableData = tableRows[i].getElementsByTagName("td");

		for(var j=0; j < tableData.length; j++)
		{
			var newTableRow = document.createElement("tr");
			if(j==0&&i!=0)
			{
				var data = tableData[10].innerHTML;
			data = data.replace(/(\r\n|\n|\r)/gm,"");
			
			if(data.trim().indexOf(string)!=-1)
				{
				for(var s=0;s<tableData.length;s++)
				{
					var isDel=-1;
					for(var t=0;t<prevdel.length;t++)
					{

					if(prevdel[t]==s)
						isDel++;
					}
				if(isDel==-1)
				newTableRow.appendChild(tableData[s].cloneNode(true));
				}
				newTableBody.appendChild(newTableRow.cloneNode(true));
				}
			}
			
			}

	}
	table1.replaceChild(newTableBody, table1Body);		
}


var prevdel=[];
var selectedinit=[];
function selected(column)
{

	for(var k=0;k<26;k++)
	{
		if(k==1||k==2||k==3||k==4||k==5||k==6||k==7||k==9||k==11||k==12||k==13||k==14||k==15||k==16||k==17||k==18||k==19||k==20||k==22||k==25)	
					{
				var selected=document.getElementById("Filter".concat(k));
				selectedinit.push(selected.selectedIndex);
			}
}
	
	var table = document.getElementById("newTable");
	var table1 =document.getElementById("fullTable");
	var tableBody = table.getElementsByTagName("tbody")[0]; 
	var table1Body = table1.getElementsByTagName("tbody")[0]; 
	var tableRows = tableBody.getElementsByTagName("tr");
	var table1Rows = table1Body.getElementsByTagName("tr");
	var newTableBody = document.createElement("tbody");
	var string = 'Select';
	var isPresent=-1;
	string = string.concat(column);
	var element = document.getElementById(string);
	var prevdeltemp=[];
	if(element.checked==1)
	{
		
		prevdel=prevdel.reverse();
		var size = prevdel.length;
		for(var i =0;i<size;i++)
		{
		var temp = prevdel.pop();
		if(temp!=column)
			prevdeltemp.push(temp);
		}
		prevdel = prevdeltemp;


		var head = document.getElementById("th".concat(column));
		head.hidden = false;
		
		
		for (var i=0; i < 1; i++)
		{ 
			var newTableRow = document.createElement("tr");
			
		var tableData = tableRows[i].getElementsByTagName("td");
		
			for(var j=0; j < tableData.length; j++)
			{
			isPresent=-1;
			for(var k=0;k<prevdel.length;k++)
			{	if(prevdel[k]==j)
					isPresent++;	
			}	
			if(isPresent==-1)
				newTableRow.appendChild(tableData[j].cloneNode(true));
			
			}
		newTableBody.appendChild(newTableRow.cloneNode(true));
		}
		
		for(var m=1;m<table1Rows.length;m++)
		{
			var newTableRow = document.createElement("tr");
			var table1Data = table1Rows[m].getElementsByTagName("td");
			for (var i=1; i < tableRows.length; i++)
			{ 
			var tableData = tableRows[i].getElementsByTagName("td");
			
			if(tableData[0].innerHTML==table1Data[0].innerHTML)	
			{
				for(var j=0; j < tableData.length; j++)
				{
				isPresent=-1;
				for(var k=0;k<prevdel.length;k++)
				{	if(prevdel[k]==j)
						isPresent++;	
				}	
				if(isPresent==-1)
					newTableRow.appendChild(tableData[j].cloneNode(true));
				
				}
			newTableBody.appendChild(newTableRow.cloneNode(true));
				}
			}
			}
		table1.replaceChild(newTableBody, table1Body);		
	}
	
	
	else if(element.checked==0)
	{
		var number=0;
		for(var i =0;i<prevdel.length;i++)
			{
			if(prevdel[i]<column)
				number++;
			}
			prevdel.push(column);
		
			var head = document.getElementById("th".concat(column));
		head.hidden = true;
		column-=number;
	for (var i=0; i < table1Rows.length; i++)
	{ 
		var tableData = table1Rows[i].getElementsByTagName("td");
		var newTableRow = document.createElement("tr");
		for(var j=0; j < tableData.length; j++)
		{
			
			
			if(j!=column)
			{
				newTableRow.appendChild(tableData[j].cloneNode(true));
				}
			}
		newTableBody.appendChild(newTableRow.cloneNode(true));
			}
	table1.replaceChild(newTableBody, table1Body);
		}
	for(var k=26;k>-1;k--)
	{
		if(k==1||k==2||k==3||k==4||k==5||k==6||k==7||k==9||k==11||k==12||k==13||k==14||k==15||k==16||k==17||k==18||k==19||k==20||k==22||k==25)	
			{
			var eraseelement = ('Filter').concat(k);
			var eraseselect = document.getElementById(eraseelement);
			var sel = selectedinit.pop();
			eraseselect.options[sel].selected = true;
				}
				
	}
	
	}
var isFilter=-1;

function openFilter()
{
	var table = document.getElementById("filterTable");
	
	if(isFilter==-1)
		{
		isFilter=1;
		table.hidden=true;
		}
	else
		{
		isFilter=-1;
		table.hidden=false;
		}
}




var listprev=[];
var selectedall =[];

var prevcol = -1;
function getList(columnnumber)
{
	
	
	

	var column=columnnumber;
	var number=0;
	for(var i=0;i<prevdel.length;i++)
		{
		if(prevdel[i]<columnnumber)
			number++;
		}
	
	column-=number;
	if(prevcol!=columnnumber)
		{
		var table = document.getElementById("newTable");
		var table1 =document.getElementById("fullTable");
		var tableBody = table.getElementsByTagName("tbody")[0]; 
		var table1Body = table1.getElementsByTagName("tbody")[0]; 
		var tableRows = table1Body.getElementsByTagName("tr");
		//var tableData = tableBody.getElementsByTagName("td");
		var selected=document.getElementById("Filter".concat(columnnumber));
		var string = selected.options[selected.selectedIndex].text;
		var FilteredTableBody = document.createElement("tbody");
		string = string.replace(/(\r\n|\n|\r)/gm,"");
		if(string.trim()==""||string.trim()=="0")
			{
		
			var element = ('Filter').concat(columnnumber);
			if(prevcol!=-1)
			{	
			var select = document.getElementById(element);
			for (var i=0; i<select.options.length; i++)
			 {
			  if ( select.options[i].value == "" )
			  {
				for(var i=0;i<listprev.length;i++) 
				{
					var ele = listprev.pop();
					var eraseelement = ('Filter').concat(ele);
					var eraseselect = document.getElementById(eraseelement);
					eraseselect.options[0].selected = true;
			   isFound = true;
			   }
			  }
			 }
			}
			
			table1.replaceChild(tableBody, table1Body);
			}
		
		
		else{
		for (var i=0; i < tableRows.length; i++)
		{ 
			var eraseelement = ('Filter').concat(columnnumber);
			var eraseselect = document.getElementById(eraseelement);
		
			
			if(i==0)		
			{	
				
			if(i==0)
				for(var k=0;k<26;k++)
					{
					if(k==1||k==2||k==3||k==4||k==5||k==6||k==7||k==9||k==11||k==12||k==13||k==14||k==15||k==16||k==17||k==18||k==19||k==20||k==22||k==25)	
						
							{
								selected=document.getElementById("Filter".concat(k));
								
								selectedall.push(selected.selectedIndex);

							}
					
				}
				FilteredTableBody.appendChild(tableRows[i].cloneNode(true));
				
			}
			var tableData = tableRows[i].getElementsByTagName("td");
			for(var j=0; j < tableData.length; j++)
			{

				if(j==0&&i!=0)
				{
					var data = tableData[column].innerHTML;

					data = data.replace(/(\r\n|\n|\r)/gm,"");
				if(data.trim()==string.trim()||((columnnumber==11||columnnumber==13||columnnumber==14||columnnumber==15)&& data.trim().indexOf(string.trim())!=-1))
					{
					FilteredTableBody.appendChild(tableRows[i].cloneNode(true));
					}
				}
				}
			}
			table1.replaceChild(FilteredTableBody, table1Body);
			for(k=26;k>-1;k--)
			{
				if(k==1||k==2||k==3||k==4||k==5||k==6||k==7||k==9||k==11||k==12||k==13||k==14||k==15||k==16||k==17||k==18||k==19||k==20||k==22||k==25)	
						{
					eraseelement = ('Filter').concat(k);
					eraseselect = document.getElementById(eraseelement);
					
					var sel = selectedall.pop();

					eraseselect.options[sel].selected = true;
					}
					
		}		
			
		}			listprev.push(prevcol);
		prevcol = columnnumber;

		}
	
	else
		{
		
	var table = document.getElementById("newTable");
	var table1 =document.getElementById("fullTable");
	var tableBody = table.getElementsByTagName("tbody")[0]; 
	var table1Body = table1.getElementsByTagName("tbody")[0]; 
	var tableRows = tableBody.getElementsByTagName("tr");
	var table1Rows = table1Body.getElementsByTagName("tr");
	var selected=document.getElementById("Filter".concat(columnnumber));
	var string = selected.options[selected.selectedIndex].text;
	var FilteredTableBody = document.createElement("tbody");
	string = string.replace(/(\r\n|\n|\r)/gm,"");
	if(string.trim()==""||string.trim()=="0")
	{

	var element = ('Filter').concat(columnnumber);
	if(prevcol!=-1)
	{	
		
	var select = document.getElementById(element);
	for (var i=0; i<select.options.length; i++)
	 {
	  if ( select.options[i].value == "" ||select.options[i].value=="0")
	  {
		for(var i=0;i<listprev.length;i++) 
		{
			var ele = i;
			var eraseelement = ('Filter').concat(ele);
			if(eraseselect!=null)
			{var eraseselect = document.getElementById(eraseelement);
			eraseselect.options[0].selected = true;
	   isFound = true;
			}
	   }
	  }
	 }
	
	}
	prevcol=-1;
	
	for(var i=0;i<tableRows.length;i++)
	{
		var newTableRow = document.createElement("tr");
		var tableData = tableRows[i].getElementsByTagName("td");
		for(var s=0;s<tableData.length;s++)
		{
			var isDel=-1;
			for(var t=0;t<prevdel.length;t++)
			{

			if(prevdel[t]==s)
				{
				isDel++;
				
				}
			}
			
		if(isDel==-1)
		newTableRow.appendChild(tableData[s].cloneNode(true));
		}
		FilteredTableBody.appendChild(newTableRow.cloneNode(true));
		}
	
	table1.replaceChild(FilteredTableBody, table1Body);
	}

else{

for (var i=0; i < tableRows.length; i++)
{ 
	var eraseelement = ('Filter').concat(columnnumber);
	var eraseselect = document.getElementById(eraseelement);
	
	var selected;
	if(i==0)		
	{	
		
	if(i==0)
		for(var k=0;k<eraseselect.options.length;k++)
			{
			if(eraseselect.options[k].innerHTML==string)
				{
				selected =k;
				
				}
			
		}
	
	var newTableRow = document.createElement("tr");

	var tableData = tableRows[i].getElementsByTagName("td");
	for(var s=0;s<tableData.length;s++)
		{
			var isDel=-1;
			for(var t=0;t<prevdel.length;t++)
			{

			if(prevdel[t]==s)
				isDel++;
			}
		if(isDel==-1)
		newTableRow.appendChild(tableData[s].cloneNode(true));
		}
		FilteredTableBody.appendChild(newTableRow.cloneNode(true));

		
	}

	var tableData = tableRows[i].getElementsByTagName("td");
	
	for(var j=0; j < tableData.length; j++)
	{
		var newTableRow = document.createElement("tr");
		if(j==0&&i!=0)
		{
			var data = tableData[columnnumber].innerHTML;
		data = data.replace(/(\r\n|\n|\r)/gm,"");

		if(data.trim()==string.trim()||((columnnumber==11||columnnumber==13||columnnumber==14||columnnumber==15)&& data.trim().indexOf(string.trim())!=-1))
			{
			
			for(var s=0;s<tableData.length;s++)
			{
				var isDel=-1;
				for(var t=0;t<prevdel.length;t++)
				{

				if(prevdel[t]==s)
					isDel++;
				}
			if(isDel==-1)
			newTableRow.appendChild(tableData[s].cloneNode(true));
			}
			FilteredTableBody.appendChild(newTableRow.cloneNode(true));
			}
		}
		
		}

	}
table1.replaceChild(FilteredTableBody, table1Body);

for(var i=0;i<listprev.length-1;i++) 
{

	var ele = listprev.pop();
	var eraseelement = ('Filter').concat(ele);
	var eraseselect = document.getElementById(eraseelement);
	eraseselect.options[0].selected = true;
isFound = true;
}
eraseelement = ('Filter').concat(columnnumber);
eraseselect = document.getElementById(eraseelement);

eraseselect.options[selected].selected = true;
listprev.push(prevcol);
prevcol = columnnumber;


}	
		}
	
		
};
</script>

</head>



<body bgcolor="#EBE6E5">
<div id="inithid">
<P>
<input type="button" Style="border:1px;width:300px;background-color:#EBE6E5">
<INPUT  Style="color:white;background-color:#002381;border-radius: 5px;-moz-border-radius:5px;"  Value = "Filter Table" onClick="openFilter() " TYPE='SUBMIT'>
<input type="button" Style="border:1px;width:300px;background-color:#EBE6E5">
<%String nextpage = "View1Only.jsp";
if(session.getAttribute("currentURLMain")!=null)
	nextpage = session.getAttribute("currentURLMain").toString();
out.println("<INPUT  Style=\"color:white;background-color:#002381;border-radius: 5px;-moz-border-radius:5px;\"  Value = \"Main Table\" onClick=\"window.location='"+nextpage+"' \" TYPE='SUBMIT'>");
%>
<input type="button" Style="border:1px;width:500px;background-color:#EBE6E5">
<a href="ViewMain.jsp"><img src="home.jpg" width="40" height="40" border="2" style='border-radius: 35px;-moz-border-radius:35px;border: 3px black solid;'/></a>
</P>
<br>
<table cellpadding="8" ><tr>
<td><input type="button" Style="border:1px;width:1100px;background-color:#EBE6E5"></td>
<td  style='border-width: 2px;border-radius: 35px;-moz-border-radius:35px;background-color: #C3BEBD;'>
Mfgn Search: <input type="text" style='border-width: 5px;border-radius: 35px;-moz-border-radius:35px;background-color:#FFFFFF;' id = "searchBar" width="30" height="2" onkeyup="search()"/>
</td></tr></table>
<br>
<table ><tr>
<td valign="top"><table width=200  id ="filterTable" style='background-color: #C3BEBD;border: 2px solid black;border-radius: 10px;-moz-border-radius:10px; border-style: solid;'  RULES=NONE FRAME=BOX >
<tr><td><input type="checkbox" checked="checked" id = "Select1" onChange="selected(1)" />Quarter</td></tr>
<tr><td><input type="checkbox" checked="checked" id = "Select2" onChange="selected(2)"/>Load Date</td></tr>
<tr><td><input type="checkbox" checked="checked" id = "Select3" onChange="selected(3)"/>Load Week</td></tr>
<tr><td><input type="checkbox" checked="checked" id = "Select4" onChange="selected(4)"/>Shipped Date</td></tr>
<tr><td><input type="checkbox" checked="checked" id = "Select5" onChange="selected(5)"/>Cancelled Date</td></tr>
<tr><td><input type="checkbox" checked="checked" id = "Select6" onChange="selected(6)"/>Status Week</td></tr>
<tr><td><input type="checkbox" checked="checked" id = "Select7" onChange="selected(7)"/>Order Category</td></tr>
<tr><td><input type="checkbox" checked="checked" id = "Select8" onChange="selected(8)"/>Order Priority</td></tr>
<tr><td><input type="checkbox" checked="checked" id = "Select9" onChange="selected(9)"/>Test Cell</td></tr>
<tr><td><input type="checkbox" checked="checked" id = "Select10" onChange="selected(10)"/>Mfg#</td></tr>
<tr><td><input type="checkbox" checked="checked" id = "Select11" onChange="selected(11)"/>Order#</td></tr>
<tr><td><input type="checkbox" checked="checked" id = "Select12" onChange="selected(12)"/>Order Type</td></tr>
<tr><td><input type="checkbox" checked="checked" id = "Select13" onChange="selected(13)"/>Model</td></tr>
<tr><td><input type="checkbox" checked="checked" id = "Select14" onChange="selected(14)"/>Model Category</td></tr>
<tr><td><input type="checkbox" checked="checked" id = "Select15" onChange="selected(15)"/>Model#</td></tr>
<tr><td><input type="checkbox" checked="checked" id = "Select16" onChange="selected(16)"/>Product Line</td></tr>
<tr><td><input type="checkbox" checked="checked" id = "Select17" onChange="selected(17)"/>Customer</td></tr>
<tr><td><input type="checkbox" checked="checked" id = "Select18" onChange="selected(18)"/>Country</td></tr>
<tr><td><input type="checkbox" checked="checked" id = "Select19" onChange="selected(19)"/>PSSD</td></tr>
<tr><td><input type="checkbox" checked="checked" id = "Select20" onChange="selected(20)"/>RSSD</td></tr>
<tr><td><input type="checkbox" checked="checked" id = "Select21" onChange="selected(21)"/>Config(F / N / D)</td></tr>
<tr><td><input type="checkbox" checked="checked" id = "Select22" onChange="selected(22)"/>FOE</td></tr>
<tr><td><input type="checkbox" checked="checked" id = "Select23" onChange="selected(23)"/>TEST Time Rem</td></tr>
<tr><td><input type="checkbox" checked="checked" id = "Select24" onChange="selected(24)"/>TEST Time</td></tr>
<tr><td><input type="checkbox" checked="checked" id = "Select25" onChange="selected(25)"/>Status</td></tr>
<tr><td><input type="checkbox" checked="checked" id = "Select26" onChange="selected(26)"/>Remarks</td></tr>
<tr><td><input type="checkbox" checked="checked" id = "Select27" onChange="selected(27)"/>Kit Short</td></tr>
<tr><td><input type="checkbox" checked="checked" id = "Select28" onChange="selected(28)"/>Installation Short</td></tr>
<tr><td><input type="checkbox" checked="checked" id = "Select29" onChange="selected(29)"/>Genuine Short</td></tr>
<tr><td><input type="checkbox" checked="checked" id = "Select30" onChange="selected(30)"/>FRAME</td></tr>
<tr><td><input type="checkbox" checked="checked" id = "Select31" onChange="selected(31)"/>NODE</td></tr>
<tr><td><input type="checkbox" checked="checked" id = "Select32" onChange="selected(32)"/>DRAWER</td></tr>
<tr><td><input type="checkbox" checked="checked" id = "Select33" onChange="selected(33)"/>SGR</td></tr>
<tr><td><input type="checkbox" checked="checked" id = "Select34" onChange="selected(34)"/>MES</td></tr>
<tr><td><input type="checkbox" checked="checked" id = "Select35" onChange="selected(35)"/>HGT</td></tr>
<tr><td><input type="checkbox" checked="checked" id = "Select36" onChange="selected(36)"/>SCVR</td></tr>
<tr><td><input type="checkbox" checked="checked" id = "Select37" onChange="selected(37)"/>CVR</td></tr>
<tr><td><input type="checkbox" checked="checked" id = "Select38" onChange="selected(38)"/>RPDS</td></tr>
</table></td>
<td valign="top">
<table border="black" cellpadding="0" id = "fullTable"  RULES=NONE FRAME=BOX style='border: 1px solid black;border-radius: 10px;-moz-border-radius:10px; border-style: solid; background-color: #000000;' >
<thead>

<tr>
<th style='font-family: Arial;font-size: 10pt;background-color: #C3BEBD' id="th0">Order Seq#</th>
<th style='font-family: Arial;font-size: 10pt;background-color: #C3BEBD' id="th1">Quarter</th>
<th style='font-family: Arial;font-size: 10pt;background-color: #C3BEBD' id="th2">Load date</th>
<th style='font-family: Arial;font-size: 10pt;background-color: #C3BEBD' id="th3">Load week</th>
<th style='font-family: Arial;font-size: 10pt;background-color: #C3BEBD' id="th4">Shipped date</th>
<th style='font-family: Arial;font-size: 10pt;background-color: #C3BEBD' id="th5">Cancelled date</th>
<th style='font-family: Arial;font-size: 10pt;background-color: #C3BEBD' id="th6">Status week</th>
<th style='font-family: Arial;font-size: 10pt;background-color: #C3BEBD' id="th7">    Order Category</th>
<th style='font-family: Arial;font-size: 10pt;background-color: #C3BEBD' id="th8">    Order Priority<br>
<th style='font-family: Arial;font-size: 10pt;background-color: #C3BEBD' id="th9">    Test Cell</th>
<th style='font-family: Arial;font-size: 10pt;background-color: #C3BEBD' id="th10">    Mfg#</th>
<th style='font-family: Arial;font-size: 10pt;background-color: #C3BEBD' id="th11">    Order#</th>
<th style='font-family: Arial;font-size: 10pt;background-color: #C3BEBD' id="th12">    Order Type</th>
<th style='font-family: Arial;font-size: 10pt;background-color: #C3BEBD' id="th13">	Model</th>
<th style='font-family: Arial;font-size: 10pt;background-color: #C3BEBD' id="th14">	Model Category</th>
<th style='font-family: Arial;font-size: 10pt;background-color: #C3BEBD' id="th15">    Model#</th>
<th style='font-family: Arial;font-size: 10pt;background-color: #C3BEBD' id="th16">    Product Line</th>
<th style='font-family: Arial;font-size: 10pt;background-color: #C3BEBD' id="th17">    Customer</th>
<th style='font-family: Arial;font-size: 10pt;background-color: #C3BEBD' id="th18">    Country</th>
<th style='font-family: Arial;font-size: 10pt;background-color: #C3BEBD' id="th19">PSSD</th>
<th style='font-family: Arial;font-size: 10pt;background-color: #C3BEBD' id="th20">    RSSD</th>
<th style='font-family: Arial;font-size: 10pt;background-color: #C3BEBD' id="th21">    Config(F / N / D)</th>
<th style='font-family: Arial;font-size: 10pt;background-color: #C3BEBD' id="th22">    FOE</th>
<th style='font-family: Arial;font-size: 10pt;background-color: #C3BEBD' id="th23">TEST Time Rem<P></th>
<th style='font-family: Arial;font-size: 10pt;background-color: #C3BEBD' id="th24">    TEST Time		</th>
<th style='font-family: Arial;font-size: 10pt;background-color: #C3BEBD' id="th25">    Status</th>
<th style='font-family: Arial;font-size: 10pt;background-color: #C3BEBD' id="th26">    Remarks</th>
<th style='font-family: Arial;font-size: 10pt;background-color: #C3BEBD' id="th27">    Kit Short</th>
<th style='font-family: Arial;font-size: 10pt;background-color: #C3BEBD' id="th28">    Installation Short</th>
<th style='font-family: Arial;font-size: 10pt;background-color: #C3BEBD' id="th29">Genuine Short</th>
<th style='font-family: Arial;font-size: 10pt;background-color: #C3BEBD' id="th30">    FRAME</th>
<th style='font-family: Arial;font-size: 10pt;background-color: #C3BEBD' id="th31">    NODE</th>
<th style='font-family: Arial;font-size: 10pt;background-color: #C3BEBD' id="th32">    DRAWER</th>
<th style='font-family: Arial;font-size: 10pt;background-color: #C3BEBD' id="th33">    SGR</th>
<th style='font-family: Arial;font-size: 10pt;background-color: #C3BEBD' id="th34">    MES</th>
<th style='font-family: Arial;font-size: 10pt;background-color: #C3BEBD' id="th35">    HGT</th>
<th style='font-family: Arial;font-size: 10pt;background-color: #C3BEBD' id="th36">    SCVR</th>
<th style='font-family: Arial;font-size: 10pt;background-color: #C3BEBD' id="th37">    CVR</th>
<th style='font-family: Arial;font-size: 10pt;background-color: #C3BEBD' id="th38">    RPDS</th>
</tr>
</thead>
<tbody>
<tr>
<td style='background-color: #C3BEBD'></td>
<td style='background-color: #C3BEBD'><select  style='width:40px;overflow:hidden;border-radius: 5px;-moz-border-radius:5px;background-color:#F5F1F0;' onchange = "getList(1)" id="Filter1">
<option value=" "></option>
<%
	ArrayList<String> Uval;
	 Uval = cancelled.getUniqueCol(0);
	for(int k=0;k<Uval.size();k++)
	{
		out.println("<option value="+Uval.get(k)+">"+Uval.get(k)+"</option>");
	}
	 %>
</select><br></td>
<td style='background-color: #C3BEBD'><select  style='width:40px;overflow:hidden;border-radius: 5px;-moz-border-radius:5px;background-color:#F5F1F0;' onchange = "getList(2)" id="Filter2">
<option value=" "></option>
<%
	 Uval = cancelled.getUniqueCol(1);
	for(int k=0;k<Uval.size();k++)
	{
		out.println("<option value="+Uval.get(k)+">"+Uval.get(k)+"</option>");
	}
	 %>
</select><br></td>
<td style='background-color: #C3BEBD'>
<select  style='width:40px;overflow:hidden;border-radius: 5px;-moz-border-radius:5px;background-color:#F5F1F0;' onchange = "getList(3)" id="Filter3">
<option value=" "></option>
<%
	 Uval = cancelled.getUniqueCol(2);
	for(int k=0;k<Uval.size();k++)
	{
		out.println("<option value="+Uval.get(k)+">"+Uval.get(k)+"</option>");
	}
	 %>
</select><br></td>
<td style='background-color: #C3BEBD'>
<select  style='width:40px;overflow:hidden;border-radius: 5px;-moz-border-radius:5px;background-color:#F5F1F0;' onchange = "getList(4)" id="Filter4">
<option value=" "></option>
<%
	 Uval = cancelled.getUniqueCol(3);
	for(int k=0;k<Uval.size();k++)
	{
		out.println("<option value="+Uval.get(k)+">"+Uval.get(k)+"</option>");
	}
	 %>
</select><br></td>
<td style='background-color: #C3BEBD'>
<select  style='width:40px;overflow:hidden;border-radius: 5px;-moz-border-radius:5px;background-color:#F5F1F0;' onchange = "getList(5)" id="Filter5">
<option value=" "></option>
<%
	 Uval = cancelled.getUniqueCol(4);
	for(int k=0;k<Uval.size();k++)
	{
		out.println("<option value="+Uval.get(k)+">"+Uval.get(k)+"</option>");
	}
	 %>
</select><br></td>
<td style='background-color: #C3BEBD'>
<select  style='width:40px;overflow:hidden;border-radius: 5px;-moz-border-radius:5px;background-color:#F5F1F0;' onchange = "getList(6)" id="Filter6">
<option value=" "></option>
<%
	 Uval = cancelled.getUniqueCol(5);
	for(int k=0;k<Uval.size();k++)
	{
		out.println("<option value="+Uval.get(k)+">"+Uval.get(k)+"</option>");
	}
	 %>
</select ><br></td>
<td style='background-color: #C3BEBD'><select  style='width:40px;overflow:hidden;border-radius: 5px;-moz-border-radius:5px;background-color:#F5F1F0;resize:none;' onchange = "getList(7)" id="Filter7">
<option value=" "></option>
<%
	 Uval = cancelled.getUniqueCol(6);
	for(int k=0;k<Uval.size();k++)
	{
		out.println("<option value="+Uval.get(k)+">"+Uval.get(k)+"</option>");
	}
	 %>
</select><br></td>
<td style='background-color: #C3BEBD'>
<td style='background-color: #C3BEBD'>
<select  style='width:40px;overflow:hidden;border-radius: 5px;-moz-border-radius:5px;background-color:#F5F1F0;' onchange = "getList(9)" id="Filter9">
<option value=" "></option>
<%
	 Uval = cancelled.getUniqueCol(8);
	for(int k=0;k<Uval.size();k++)
	{
		out.println("<option value="+Uval.get(k)+">"+Uval.get(k)+"</option>");
	}
	 %>
</select><br></td>
<td style='background-color: #C3BEBD'></td>
<td style='background-color: #C3BEBD'><select  style='width:40px;overflow:hidden;border-radius: 5px;-moz-border-radius:5px;background-color:#F5F1F0;' onchange = "getList(11)"id="Filter11">
<option value=" "></option>
<%
	 Uval = cancelled.getUniqueCol(10);
	for(int k=0;k<Uval.size();k++)
	{
		out.println("<option value="+Uval.get(k)+">"+Uval.get(k)+"</option>");
	}
	 %>
</select><br></td>
<td style='background-color: #C3BEBD'>
<select  style='width:40px;overflow:hidden;border-radius: 5px;-moz-border-radius:5px;background-color:#F5F1F0;' onchange = "getList(12)" id="Filter12">
<option value=" "></option>
<%
	 Uval = cancelled.getUniqueCol(11);
	for(int k=0;k<Uval.size();k++)
	{
		out.println("<option value="+Uval.get(k)+">"+Uval.get(k)+"</option>");
	}
	 %>
</select><br></td>
<td style='background-color: #C3BEBD'>
<select  style='width:40px;overflow:hidden;border-radius: 5px;-moz-border-radius:5px;background-color:#F5F1F0;' onchange = "getList(13)" id="Filter13">
<option value=" "></option>
<%
	 Uval = cancelled.getUniqueCol(12);
	for(int k=0;k<Uval.size();k++)
	{
		out.println("<option value="+Uval.get(k)+">"+Uval.get(k)+"</option>");
	}
	 %>
</select><br></td>
<td style='background-color: #C3BEBD'><select  style='width:40px;overflow:hidden;border-radius: 5px;-moz-border-radius:5px;background-color:#F5F1F0;' onchange = "getList(14)" id="Filter14">
<option value=" "></option>
<%
	 Uval = cancelled.getUniqueCol(13);
	for(int k=0;k<Uval.size();k++)
	{
		out.println("<option value="+Uval.get(k)+">"+Uval.get(k)+"</option>");
	}
	 %>
</select></td>
<td style='background-color: #C3BEBD'><select  style='width:40px;overflow:hidden;border-radius: 5px;-moz-border-radius:5px;background-color:#F5F1F0;' onchange = "getList(15)" id="Filter15">
<option value=" "></option>
<%
	 Uval = cancelled.getUniqueCol(14);
	for(int k=0;k<Uval.size();k++)
	{
		out.println("<option value="+Uval.get(k)+">"+Uval.get(k)+"</option>");
	}
	 %>
</select></td>
<td style='background-color: #C3BEBD'>
<select  style='width:40px;overflow:hidden;border-radius: 5px;-moz-border-radius:5px;background-color:#F5F1F0;' onchange = "getList(16)" id="Filter16">
<option value=" "></option>
<%
	 Uval = cancelled.getUniqueCol(15);
	for(int k=0;k<Uval.size();k++)
	{
		out.println("<option value="+Uval.get(k)+">"+Uval.get(k)+"</option>");
	}
	 %>
</select><br></td>
<td style='background-color: #C3BEBD'>
<select  style='width:40px;overflow:hidden;border-radius: 5px;-moz-border-radius:5px;background-color:#F5F1F0;' onchange = "getList(17)" id="Filter17">
<option value=" "></option>
<%
	 Uval = cancelled.getUniqueCol(16);
	for(int k=0;k<Uval.size();k++)
	{
		out.println("<option value="+Uval.get(k)+">"+Uval.get(k)+"</option>");
	}
	 %>
</select><br></td>
<td style='background-color: #C3BEBD'><select  style='width:40px;overflow:hidden;border-radius: 5px;-moz-border-radius:5px;background-color:#F5F1F0;' onchange = "getList(18)" id="Filter18">
<option value=" "></option>
<%
	 Uval = cancelled.getUniqueCol(17);
	for(int k=0;k<Uval.size();k++)
	{
		out.println("<option value="+Uval.get(k)+">"+Uval.get(k)+"</option>");
	}
	 %>
</select><br></td>
<td style='background-color: #C3BEBD'><select  style='width:40px;overflow:hidden;border-radius: 5px;-moz-border-radius:5px;background-color:#F5F1F0;' onchange = "getList(19)" id="Filter19">
<option value=" "></option>
<%
	 Uval = cancelled.getUniqueCol(18);
	for(int k=0;k<Uval.size();k++)
	{
		out.println("<option value="+Uval.get(k)+">"+Uval.get(k)+"</option>");
	}
	 %>
</select><br></td>
<td style='background-color: #C3BEBD'>
<select  style='width:40px;overflow:hidden;border-radius: 5px;-moz-border-radius:5px;background-color:#F5F1F0;' onchange = "getList(20)" id="Filter20">
<option value=" "></option>
<%
	 Uval = cancelled.getUniqueCol(19);
	for(int k=0;k<Uval.size();k++)
	{
		out.println("<option value="+Uval.get(k)+">"+Uval.get(k)+"</option>");
	}
	 %>
</select><br></td>
<td style='background-color: #C3BEBD'></td>
<td style='background-color: #C3BEBD'>
<select  style='width:40px;overflow:hidden;border-radius: 5px;-moz-border-radius:5px;background-color:#F5F1F0;' onchange = "getList(22)" id="Filter22">
<option value=" "></option>
<%
	 Uval = cancelled.getUniqueCol(21);
	for(int k=0;k<Uval.size();k++)
	{
		out.println("<option value="+Uval.get(k)+">"+Uval.get(k)+"</option>");
	}
	 %>
</select><br></td>
<td style='background-color: #C3BEBD'><td style='background-color: #C3BEBD'></td>
<td style='background-color: #C3BEBD'>
<select  style='width:40px;overflow:hidden;border-radius: 5px;-moz-border-radius:5px;background-color:#F5F1F0;' onchange = "getList(25)" id="Filter25">
<option value=" "></option>
<%
	 Uval = cancelled.getUniqueCol(24);
	for(int k=0;k<Uval.size();k++)
	{
		out.println("<option value="+Uval.get(k)+">"+Uval.get(k)+"</option>");
	}
	 %>
</select><br>
</td>
<td style='background-color: #C3BEBD'></td>
<td style='background-color: #C3BEBD'></td>
<td style='background-color: #C3BEBD'></td>
<td style='background-color: #C3BEBD'></td>
<td style='background-color: #C3BEBD'></td>
<td style='background-color: #C3BEBD'></td>
<td style='background-color: #C3BEBD'></td>
<td style='background-color: #C3BEBD'></td>
<td style='background-color: #C3BEBD'></td>
<td style='background-color: #C3BEBD'></td>
<td style='background-color: #C3BEBD'></td>
<td style='background-color: #C3BEBD'></td>
<td style='background-color: #C3BEBD'></td>
</tr>



<% 


table Cancel = cancelled.cancelled;
String id="", date = "";
int rem_rowno =-1;
int rsize = Cancel.getRows();
int csize = Cancel.getColumns()-2;
for(int i=0;i<rsize;i++)
{   

    out.println("<tr>");
    out.println("<td align='middle' style='font-family: Arial;font-size: 10pt;background-color: #C3BEBD;'>"+(i+1)+"</td>");
    for(int j=0;j<csize;j++)
    {
       
        if(j==25)
        {
           out.println("<td align='middle' style='font-family: Arial;font-size: 10pt;background-color: #C3BEBD;'>");
            out.println("<textarea disabled='disabled' rows='2' cols='30' enabled='false' style='border-radius: 5px;-moz-border-radius:5px;background-color:#F5F1F0' >");
           
            out.println(Cancel.getValue(i, 25));
            out.println("</textarea>");
            out.println("</td>");

        }
        else if (j==7)
        {
            out.println("<td align='middle' style='font-family: Arial;font-size: 10pt;background-color: #C3BEBD;'>");
            out.println("<textarea disabled='disabled' rows='1' cols='3' enabled='false' style='border-radius: 5px;-moz-border-radius:5px;background-color:#F5F1F0;resize:none;' >");              
                out.println(Cancel.getValue(i, 7));
            out.println("</textarea>");
            out.println("</td>");
        }
        else{
        out.println("<td align='middle' style='font-family: Arial;font-size: 10pt;background-color: #C3BEBD;'>");
        out.println(Cancel.getValue(i,j));
        out.println("</td>");
        }
    }
}


        

%>

</tbody>

</table>

<P><input type="SUBMIT" Style="height:40px;border-radius: 5px;-moz-border-radius:5px;background-color:#99CC33;valign=top;" value="Reload Data" onClick="window.location='ViewCancelled.jsp' "> 

</P></td></tr>
</table>


<table style="visibility:hidden;" id = "newTable" >
<tbody>
<tr>
<td style='background-color: #C3BEBD'></td>
<td style='background-color: #C3BEBD'><select  style='width:40px;overflow:hidden;border-radius: 5px;-moz-border-radius:5px;background-color:#F5F1F0;' onchange = "getList(1)" id="Filter1">
<option value=" "></option>
<%
	 Uval = cancelled.getUniqueCol(0);
	for(int k=0;k<Uval.size();k++)
	{
		out.println("<option value="+Uval.get(k)+">"+Uval.get(k)+"</option>");
	}
	 %>
</select><br></td>
<td style='background-color: #C3BEBD'><select  style='width:40px;overflow:hidden;border-radius: 5px;-moz-border-radius:5px;background-color:#F5F1F0;' onchange = "getList(2)" id="Filter2">
<option value=" "></option>
<%
	 Uval = cancelled.getUniqueCol(1);
	for(int k=0;k<Uval.size();k++)
	{
		out.println("<option value="+Uval.get(k)+">"+Uval.get(k)+"</option>");
	}
	 %>
</select><br></td>
<td style='background-color: #C3BEBD'>
<select  style='width:40px;overflow:hidden;border-radius: 5px;-moz-border-radius:5px;background-color:#F5F1F0;' onchange = "getList(3)" id="Filter3">
<option value=" "></option>
<%
	 Uval = cancelled.getUniqueCol(2);
	for(int k=0;k<Uval.size();k++)
	{
		out.println("<option value="+Uval.get(k)+">"+Uval.get(k)+"</option>");
	}
	 %>
</select><br></td>
<td style='background-color: #C3BEBD'>
<select  style='width:40px;overflow:hidden;border-radius: 5px;-moz-border-radius:5px;background-color:#F5F1F0;' onchange = "getList(4)" id="Filter4">
<option value=" "></option>
<%
	 Uval = cancelled.getUniqueCol(3);
	for(int k=0;k<Uval.size();k++)
	{
		out.println("<option value="+Uval.get(k)+">"+Uval.get(k)+"</option>");
	}
	 %>
</select><br></td>
<td style='background-color: #C3BEBD'>
<select  style='width:40px;overflow:hidden;border-radius: 5px;-moz-border-radius:5px;background-color:#F5F1F0;' onchange = "getList(5)" id="Filter5">
<option value=" "></option>
<%
	 Uval = cancelled.getUniqueCol(4);
	for(int k=0;k<Uval.size();k++)
	{
		out.println("<option value="+Uval.get(k)+">"+Uval.get(k)+"</option>");
	}
	 %>
</select><br></td>
<td style='background-color: #C3BEBD'>
<select  style='width:40px;overflow:hidden;border-radius: 5px;-moz-border-radius:5px;background-color:#F5F1F0;' onchange = "getList(6)" id="Filter6">
<option value=" "></option>
<%
	 Uval = cancelled.getUniqueCol(5);
	for(int k=0;k<Uval.size();k++)
	{
		out.println("<option value="+Uval.get(k)+">"+Uval.get(k)+"</option>");
	}
	 %>
</select ><br></td>
<td style='background-color: #C3BEBD'><select  style='width:40px;overflow:hidden;border-radius: 5px;-moz-border-radius:5px;background-color:#F5F1F0;resize:none;' onchange = "getList(7)" id="Filter7">
<option value=" "></option>
<%
	 Uval = cancelled.getUniqueCol(6);
	for(int k=0;k<Uval.size();k++)
	{
		out.println("<option value="+Uval.get(k)+">"+Uval.get(k)+"</option>");
	}
	 %>
</select><br></td>
<td style='background-color: #C3BEBD'>
<td style='background-color: #C3BEBD'>
<select  style='width:40px;overflow:hidden;border-radius: 5px;-moz-border-radius:5px;background-color:#F5F1F0;' onchange = "getList(9)" id="Filter9">
<%
	 Uval = cancelled.getUniqueCol(8);
	for(int k=0;k<Uval.size();k++)
	{
		out.println("<option value="+Uval.get(k)+">"+Uval.get(k)+"</option>");
	}
	 %>
</select><br></td>
<td style='background-color: #C3BEBD'></td>
<td style='background-color: #C3BEBD'><select  style='width:40px;overflow:hidden;border-radius: 5px;-moz-border-radius:5px;background-color:#F5F1F0;' onchange = "getList(11)"id="Filter11">
<option value=" "></option>
<%
	 Uval = cancelled.getUniqueCol(10);
	for(int k=0;k<Uval.size();k++)
	{
		out.println("<option value="+Uval.get(k)+">"+Uval.get(k)+"</option>");
	}
	 %>
</select><br></td>
<td style='background-color: #C3BEBD'>
<select  style='width:40px;overflow:hidden;border-radius: 5px;-moz-border-radius:5px;background-color:#F5F1F0;' onchange = "getList(12)" id="Filter12">
<option value=" "></option>
<%
	 Uval = cancelled.getUniqueCol(11);
	for(int k=0;k<Uval.size();k++)
	{
		out.println("<option value="+Uval.get(k)+">"+Uval.get(k)+"</option>");
	}
	 %>
</select><br></td>
<td style='background-color: #C3BEBD'>
<select  style='width:40px;overflow:hidden;border-radius: 5px;-moz-border-radius:5px;background-color:#F5F1F0;' onchange = "getList(13)" id="Filter13">
<option value=" "></option>
<%
	 Uval = cancelled.getUniqueCol(12);
	for(int k=0;k<Uval.size();k++)
	{
		out.println("<option value="+Uval.get(k)+">"+Uval.get(k)+"</option>");
	}
	 %>
</select><br></td>
<td style='background-color: #C3BEBD'><select  style='width:40px;overflow:hidden;border-radius: 5px;-moz-border-radius:5px;background-color:#F5F1F0;' onchange = "getList(14)" id="Filter14">
<option value=" "></option>
<%
	 Uval = cancelled.getUniqueCol(13);
	for(int k=0;k<Uval.size();k++)
	{
		out.println("<option value="+Uval.get(k)+">"+Uval.get(k)+"</option>");
	}
	 %>
</select></td>
<td style='background-color: #C3BEBD'><select  style='width:40px;overflow:hidden;border-radius: 5px;-moz-border-radius:5px;background-color:#F5F1F0;' onchange = "getList(15)" id="Filter15">
<option value=" "></option>
<%
	 Uval = cancelled.getUniqueCol(14);
	for(int k=0;k<Uval.size();k++)
	{
		out.println("<option value="+Uval.get(k)+">"+Uval.get(k)+"</option>");
	}
	 %>
</select></td>
<td style='background-color: #C3BEBD'>
<select  style='width:40px;overflow:hidden;border-radius: 5px;-moz-border-radius:5px;background-color:#F5F1F0;' onchange = "getList(16)" id="Filter16">
<option value=" "></option>
<%
	 Uval = cancelled.getUniqueCol(15);
	for(int k=0;k<Uval.size();k++)
	{
		out.println("<option value="+Uval.get(k)+">"+Uval.get(k)+"</option>");
	}
	 %>
</select><br></td>
<td style='background-color: #C3BEBD'>
<select  style='width:40px;overflow:hidden;border-radius: 5px;-moz-border-radius:5px;background-color:#F5F1F0;' onchange = "getList(17)" id="Filter17">
<option value=" "></option>
<%
	 Uval = cancelled.getUniqueCol(16);
	for(int k=0;k<Uval.size();k++)
	{
		out.println("<option value="+Uval.get(k)+">"+Uval.get(k)+"</option>");
	}
	 %>
</select><br></td>
<td style='background-color: #C3BEBD'><select  style='width:40px;overflow:hidden;border-radius: 5px;-moz-border-radius:5px;background-color:#F5F1F0;' onchange = "getList(18)" id="Filter18">
<option value=" "></option>
<%
	 Uval = cancelled.getUniqueCol(17);
	for(int k=0;k<Uval.size();k++)
	{
		out.println("<option value="+Uval.get(k)+">"+Uval.get(k)+"</option>");
	}
	 %>
</select><br></td>
<td style='background-color: #C3BEBD'><select  style='width:40px;overflow:hidden;border-radius: 5px;-moz-border-radius:5px;background-color:#F5F1F0;' onchange = "getList(19)" id="Filter19">
<option value=" "></option>
<%
	 Uval = cancelled.getUniqueCol(18);
	for(int k=0;k<Uval.size();k++)
	{
		out.println("<option value="+Uval.get(k)+">"+Uval.get(k)+"</option>");
	}
	 %>
</select><br></td>
<td style='background-color: #C3BEBD'>
<select  style='width:40px;overflow:hidden;border-radius: 5px;-moz-border-radius:5px;background-color:#F5F1F0;' onchange = "getList(20)" id="Filter20">
<option value=" "></option>
<%
	 Uval = cancelled.getUniqueCol(19);
	for(int k=0;k<Uval.size();k++)
	{
		out.println("<option value="+Uval.get(k)+">"+Uval.get(k)+"</option>");
	}
	 %>
</select><br></td>
<td style='background-color: #C3BEBD'></td>
<td style='background-color: #C3BEBD'>
<select  style='width:40px;overflow:hidden;border-radius: 5px;-moz-border-radius:5px;background-color:#F5F1F0;' onchange = "getList(22)" id="Filter22">
<option value=" "></option>
<%
	 Uval = cancelled.getUniqueCol(21);
	for(int k=0;k<Uval.size();k++)
	{
		out.println("<option value="+Uval.get(k)+">"+Uval.get(k)+"</option>");
	}
	 %>
</select><br></td>
<td style='background-color: #C3BEBD'><td style='background-color: #C3BEBD'></td>
<td style='background-color: #C3BEBD'>
<select  style='width:40px;overflow:hidden;border-radius: 5px;-moz-border-radius:5px;background-color:#F5F1F0;' onchange = "getList(25)" id="Filter25">
<option value=" "></option>
<%
	 Uval = cancelled.getUniqueCol(24);
	for(int k=0;k<Uval.size();k++)
	{
		out.println("<option value="+Uval.get(k)+">"+Uval.get(k)+"</option>");
	}
	 %>
</select><br>
</td>
<td style='background-color: #C3BEBD'></td>
<td style='background-color: #C3BEBD'></td>
<td style='background-color: #C3BEBD'></td>
<td style='background-color: #C3BEBD'></td>
<td style='background-color: #C3BEBD'></td>
<td style='background-color: #C3BEBD'></td>
<td style='background-color: #C3BEBD'></td>
<td style='background-color: #C3BEBD'></td>
<td style='background-color: #C3BEBD'></td>
<td style='background-color: #C3BEBD'></td>
<td style='background-color: #C3BEBD'></td>
<td style='background-color: #C3BEBD'></td>
<td style='background-color: #C3BEBD'></td>
</tr>

<% 




Cancel = cancelled.cancelled;
id=""; date = "";

rsize = Cancel.getRows();
csize = Cancel.getColumns()-2;
for(int i=0;i<rsize;i++)
{   

    out.println("<tr>");
    out.println("<td align='middle' style='font-family: Arial;font-size: 10pt;background-color: #C3BEBD;'>"+(i+1)+"</td>");
    for(int j=0;j<csize;j++)
    {
       
        if(j==25)
        {
           out.println("<td align='middle' style='font-family: Arial;font-size: 10pt;background-color: #C3BEBD;'>");
            out.println("<textarea disabled='disabled' rows='2' cols='30' style='border-radius: 5px;-moz-border-radius:5px;background-color:#F5F1F0' >");
           
            out.println(Cancel.getValue(i, 25));
            out.println("</textarea>");
            out.println("</td>");

        }
        else if (j==7)
        {
            out.println("<td align='middle' style='font-family: Arial;font-size: 10pt;background-color: #C3BEBD;'>");
            out.println("<textarea disabled='disabled' rows='1' cols='3' style='border-radius: 5px;-moz-border-radius:5px;background-color:#F5F1F0;resize:none;' >");              
                out.println(Cancel.getValue(i, 7));
            out.println("</textarea>");
            out.println("</td>");
        }
        else{
        out.println("<td align='middle' style='font-family: Arial;font-size: 10pt;background-color: #C3BEBD;'>");
        out.println(Cancel.getValue(i,j));
        out.println("</td>");
        }
    }
}


        
%>

</tbody>

</table>
</div>
</body>

</html>
