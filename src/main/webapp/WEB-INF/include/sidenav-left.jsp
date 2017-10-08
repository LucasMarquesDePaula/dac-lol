<%-- 
    Document   : sidenav-left
    Created on : 23/09/2017, 21:22:08
    Author     : Lucas
--%>
<%@ taglib prefix = "st" uri = "/WEB-INF/static.tld" %>

<md-sidenav id="sidenav-left" ref="self" class="md-left" :md-swipeable="true">
    <md-toolbar class="md-large">
        <div class="md-toolbar-container">
            <h3 class="md-title">Sidenav content</h3>
        </div>
    </md-toolbar>
    <md-list class="md-dense">
        <md-list-item>
            <md-icon>person</md-icon>
            <span>Clientes</span>
            <md-list-expand>
                <md-list>
                    <md-list-item class="md-inset" href="/lol/cliente/grid" >
                        <span>Listar</span>
                        <md-icon>list</md-icon> 
                    </md-list-item>
                    <md-list-item class="md-inset" href="/lol/cliente/form" >
                        <span>Adicionar</span>
                        <md-icon>add</md-icon> 
                    </md-list-item>
                </md-list>
            </md-list-expand>
        </md-list-item>
    </md-list>
</md-sidenav>
<st:js res="include/sidenav-left/sidenav-left.js"/>