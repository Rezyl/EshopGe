<html>

<head>
<meta http-equiv="Content-Language" content="en-us">
<meta http-equiv="Content-Type" content="text/html; charset=windows-1250">
<title>DobrÝ den</title>
</head>

<body>

<table style="font-family: Verdana,sans-serif; font-size: 11px; color: #374953; width: 550px">
	<tr>
		<td align="left">&nbsp;<img border="0" src="cid:${logo}" width="291" height="62"></td>
	</tr>
	<tr>
		<td>&nbsp;</td>
	</tr>
	<tr>
        <td align="left">Dobrý den, p. <strong><span style="color: #05536A">${order.surname}</span></strong></td>
	</tr>
	<tr>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td style="color: #fff; font-size: 12px; font-weight: bold; padding-left: 1em; padding-right: 1em; padding-top: 0.5em; padding-bottom: 0.5em; background-color: #05536a" align="left">
		Průběh vaší objednávky #<span lang="cs">${order.orderID}</span> </td>
	</tr>
	<tr>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td align="left">Vaše objednávka byla úspěně vytvořena.
            Ohledně dalšího postupu o předání budete co nejdříve kontaktován/a
            na uvedeném emailu/tel. čísle.

			</td>
	</tr>

	<tr>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td align="left"><br>
		Děkujeme za Vaši přízeň,<br>
		Bisonsocks.cz<span class="HOEnZb"><font color="#888888"><br>
&nbsp;</font></span></td>
	</tr>
</table>

<table style="font-family:Verdana,sans-serif;font-size:11px;color:#374953;width:550px">
	<tr>
		<td align="left">&nbsp;</td>
	</tr>
	<tr>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td align="left">
		<table style="width:100%;font-size:11px;color:#374953">
			<tr style="background-color:#b9babe;text-align:center">
				<th>Zboží</th>
				<th style="width:15%;padding:0.6em 0">Cena/kus</th>
				<th style="width:15%;padding:0.6em 0">Množství</th>
				<th style="width:20%;padding:0.6em 0">Cena</th>
			</tr>
            <#--iteration-->
            <#list shoppingItems as entry>
                    <tr style="background-color:#ebecee">
				<td style="padding:0.6em 0.4em"><strong><span lang="cs">${entry.getKey().name}</span></strong></td>
				<td style="padding:0.6em 0.4em;text-align:right"><span lang="cs">${entry.getKey().price} Kč</span></td>
				<td style="padding:0.6em 0.4em;text-align:center"><span lang="cs">${entry.getValue()}</span></td>
				<td style="padding:0.6em 0.4em;text-align:right"><span lang="cs">${entry.getValue() * entry.getKey().price} Kč</span></td>
			</tr>
            </#list>
            <#--end iteration-->
			<tr style="text-align:right">
				<td>&nbsp;</td>
				<td colspan="3" style="background-color:#b9babe;padding:0.6em 0.4em">
				Zboží</td>
				<td style="background-color:#b9babe;padding:0.6em 0.4em">${order.priceOfItems} Kč</td>
			</tr>
			<tr style="text-align:right">
				<td>&nbsp;</td>
				<td colspan="3" style="background-color:#dde2e6;padding:0.6em 0.4em">
				Poštovné a balné</td>
				<td style="background-color:#dde2e6;padding:0.6em 0.4em">0 Kč</td>
			</tr>
			<tr style="text-align:right;font-weight:bold">
				<td>&nbsp;</td>
				<td colspan="3" style="background-color:#9fad9c;padding:0.6em 0.4em">
				Cena celkem </td>
				<td style="background-color:#9fad9c;padding:0.6em 0.4em">${order.totalPrice} Kč vč DPH</td>
			</tr>
		</table>
		</td>
	</tr>
	<tr>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td style="background-color:#05536a;color:#fff;font-size:12px;font-weight:bold;padding:0.5em 1em" align="left">
		Doručení</td>
	</tr>
	<tr>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td align="left">Způsob dopravy: Osobní převzetí v Hradci Králové</td>
	</tr>
	<tr>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td>
		<table style="width:100%;font-family:Verdana,sans-serif;font-size:11px;color:#374953">
			<tr style="background-color:#b9babe;text-transform:uppercase">
				<th style="text-align:left;padding:0.3em 1em">Adresa pro 
				doručení</th>
			</tr>
			<tr>
				<td style="padding:0.5em 0 0.5em 0.5em;background-color:#ebecee">
				${order.name} ${order.surname}<br>
                ${order.street}<br>
				<br>
                ${order.city} ${order.psc}<br>
				Česká republika <br>
				<br>
				Tel.:
                ${order.mobile}</td>
			</tr>
		</table>
		</td>
	</tr>
</table>

</body>

</html>
