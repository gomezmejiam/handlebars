<div>Nombre: {{nombre}}</div>|<div class="fecha">{{fecha}}</div>
<ul>
    {{#each item}}
        <li><span> ({{cantidad}}) {{#upper tipo}}{{/upper}} </li>
    {{/each}}
</ul>