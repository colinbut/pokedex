<#-- @ftlvariable name="" type="com.mycompany.pokdedex.view.PokemonView" -->
<html>
    <body>
        <h1>${pokemon.id?html} - ${pokemon.name?html}</h1>
        <table>
            <tr><td><b>Hit Points:</b></td><td>${pokemon.hitPoints?html}</td></tr>
            <tr><td><b>Combat Power:</b></td><td>${pokemon.combatPower?html}</td></tr>
            <tr><td><b>Type:</b></td><td>${pokemon.type?html}</td></tr>
        </table>
        <h2>Attacks:</h2>
        <table>
            <tr><th>Name</th><th>Power</th><th>Accuracy</th><th>Type</th></tr>
            <#list pokemon.attacks as attack>
                <tr><td>${attack.name}</td><td>${attack.power}</td><td>${attack.accuracy}</td><td>${attack.type}</td></tr>
            </#list>
        </table>

    </body>
</html>
