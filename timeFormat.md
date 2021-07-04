 ### Параметры для `time_format`

<table width="100%" class="post_table">
 <tbody><tr>
      <td><b>Символ</b></td> <td><b>Что означает</b></td> <td><b>Пример</b></td> </tr>
<tr> <td><code>G</code></td> <td>эра (в английской локализации - AD и BC)</td>		<td>н.э.</td></tr>
<tr> <td><code>y</code></td> <td>год (4-х значное число)</td>		<td>2012</td></tr>
<tr> <td><code>yy</code></td> <td>год (последние 2 цифры)</td>		<td>12</td></tr>
<tr> <td><code>yyyy</code></td> <td>год (4-х значное число)</td>		<td>2012</td></tr>
<tr> <td><code>M</code></td> <td>номер месяца без лидирующих нулей</td>		<td>2</td></tr>
<tr> <td><code>MM</code></td> <td>номер месяца (с лидирующими нулями если номер месяца &lt; 10)</td>		<td>02</td></tr>
<tr> <td><code>MMM</code></td> <td>четырех буквенное сокращение месяца в русской локализации и трех буквенное - в английской (Feb)</td>		<td>фев</td></tr>
<tr> <td><code>MMMM</code></td> <td>полное название месяца (в английской локализации - February)</td>		<td>Февраль</td></tr>
<tr> <td><code>w</code></td> <td>неделя в году без лидирующих нулей</td>		<td>7</td></tr>
<tr> <td><code>ww</code></td> <td>неделя в году с лидирующими нулями</td>		<td>07</td></tr>
<tr> <td><code>W</code></td> <td>неделя в месяце без лидирующих нулей</td>		<td>2</td></tr>
<tr> <td><code>WW</code></td> <td>неделя в месяце с лидирующим нулем (если это необходимо)</td>		<td>02</td></tr>
<tr> <td><code>D</code></td> <td>день в году</td>		<td>38</td></tr>
<tr> <td><code>d</code></td> <td>день месяца без лидирующих нулей</td>		<td>7</td></tr>
<tr> <td><code>dd</code></td> <td>день месяца с лидирующими нулями</td>		<td>07</td></tr>
<tr> <td><code>F</code></td> <td>день недели в месяце без лидирующих нулей</td>		<td>1</td></tr>
<tr> <td><code>FF</code></td> <td>день недели в месяце с лидирующими нулями</td>		<td>01</td></tr>
<tr> <td><code>E</code></td> <td>день недели (сокращение)</td>		<td>Вт</td></tr>
<tr> <td><code>EEEE</code></td> <td>день недели (полностью)</td>		<td>вторник</td></tr>
<tr> <td><code>a</code></td> <td>AM/PM указатель</td>		<td>AM</td></tr>
<tr> <td><code>H</code></td> <td>часы в 24-часовом формате без лидирующих нулей</td>		<td>6</td></tr>
<tr> <td><code>HH</code></td> <td>часы в 24-часовом формате с лидирующим нулем</td>		<td>06</td></tr>
<tr> <td><code>k</code></td> <td>количество часов в 24-часовом формате</td>		<td>18</td></tr>
<tr> <td><code>K</code></td> <td>количество часов в 12-часовом формате</td>		<td>6</td></tr>
<tr> <td><code>h</code></td> <td>время в 12-часовом формате без лидирующих нулей</td>		<td>6</td></tr>
<tr> <td><code>hh</code></td> <td>время в 12-часовом формате с лидирующим нулем</td>		<td>06</td></tr>
<tr> <td><code>m</code></td> <td>минуты без лидирующих нулей</td>		<td>32</td></tr>
<tr> <td><code>mm</code></td> <td>минуты с лидирующим нулем</td>		<td>32</td></tr>
<tr> <td><code>s</code></td> <td>секунды без лидирующих нулей</td>		<td>11</td></tr>
<tr> <td><code>ss</code></td> <td>секунды с лидирующим нулем</td>		<td>11</td></tr>
<tr> <td><code>S</code></td> <td>миллисекунды</td>		<td>109</td></tr>
<tr> <td><code>z</code></td> <td>часовой пояс</td>		<td>EET</td></tr>
<tr> <td><code>Z</code></td> <td>часовой пояс в формате RFC 822</td>		<td>+0200</td></tr>
 <tr> <td><code>'</code></td> <td>символ экранирования для текста</td>            <td>'Date='</td> </tr>
 <tr> <td><code>''</code></td> <td>кавычка</td>                <td>'o''clock'</td> </tr>
 </tbody></table>
 
 <hr>
 
 ### Русская локализация паттернов даты и времени
 

 <table width="100%" class="post_table">
 <tbody>
    <tr>
      <td><b>Паттерн даты и времени</b></td>
      <td><b>Результат</b></td>
    </tr>
<tr> <td><code>"e;yyyy.MM.dd G 'at' HH:mm:ss z"e;</code></td>		<td>2012.02.07 н.э. at 16:51:35 EET</td></tr>
<tr> <td><code>"e;EEE, MMM d, ''yy"e;</code></td>		<td>Вт, фев 7, '12</td></tr>
<tr> <td><code>"e;h:mm a"e;</code></td>		<td>4:51 PM</td></tr>
<tr> <td><code>"e;hh 'o''clock' a, zzzz"e;</code></td>		<td>04 o'clock PM, Eastern European Time</td></tr>
<tr> <td><code>"e;K:mm a, z"e;</code></td>		<td>4:51 PM, EET</td></tr>
<tr> <td><code>"e;yyyyy.MMMMM.dd GGG hh:mm aaa"e;</code></td>		<td>02012.Февраль.07 н.э. 04:51 PM</td></tr>
<tr> <td><code>"e;EEE, d MMM yyyy HH:mm:ss Z"e;</code></td>		<td>Вт, 7 фев 2012 16:51:35 +0200</td></tr>
<tr> <td><code>"e;yyMMddHHmmssZ"e;</code></td>		<td>120207165135+0200</td></tr>
  </tbody>
</table>
