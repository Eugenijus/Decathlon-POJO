<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:template match="/">
  <html>
  <body>
    <h2>Decathlon Results</h2>
    <table border="1">
      <tr bgcolor="skyblue">
        <th>Place</th>
        <th>Name</th>
		<th>Score</th>
		<th>run100M</th>
		<th>longJump</th>
		<th>shotPutThrow</th>
		<th>highJump</th>
		<th>run400M</th>
		<th>run110MHurdles</th>
		<th>discusThrow</th>
		<th>poleVaultJump</th>
		<th>javelinThrow</th>
		<th>run1500M</th>
      </tr>
      <xsl:for-each select="athletes/athlete">
        <tr>
          <td><xsl:value-of select="place"/></td>
          <td><xsl:value-of select="name"/></td>
		  <td><xsl:value-of select="score"/></td>
			<td><xsl:value-of select="results/run100M"/></td>
			<td><xsl:value-of select="results/longJump"/></td>
			<td><xsl:value-of select="results/shotPutThrow"/></td>
			<td><xsl:value-of select="results/highJump"/></td>
			<td><xsl:value-of select="results/run400M"/></td>
			<td><xsl:value-of select="results/run110MHurdles"/></td>
			<td><xsl:value-of select="results/discusThrow"/></td>
			<td><xsl:value-of select="results/poleVaultJump"/></td>
			<td><xsl:value-of select="results/javelinThrow"/></td>
			<td><xsl:value-of select="results/run1500M"/></td>
        </tr>
      </xsl:for-each>
    </table>
  </body>
  </html>
</xsl:template>
</xsl:stylesheet>