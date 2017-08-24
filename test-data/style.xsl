<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:template match="/">
  <html>
  <body>
    <h2>Decathlon Results</h2>
    <table border="1">
      <tr bgcolor="blue">
        <th>Place</th>
        <th>Name</th>
		<th>Score</th>
		<th>Results</th>
      </tr>
      <xsl:for-each select="athletes/athlete">
        <tr>
          <td><xsl:value-of select="place"/></td>
          <td><xsl:value-of select="name"/></td>
		  <td><xsl:value-of select="score"/></td>
          <td><xsl:value-of select="results"/></td>
        </tr>
      </xsl:for-each>
    </table>
  </body>
  </html>
</xsl:template>
</xsl:stylesheet>