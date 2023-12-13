<template>
  <div id="capstone-app" class="container">
    <header>
      <img src="resources\melr.png">
    </header>
    <nav>
      <div id="nav" class="content">
        <router-link v-bind:to="{ name: 'home' }">Home</router-link>
        <router-link v-bind:to="{name: 'recipes'}">My Recipes</router-link>
        <router-link v-bind:to="{name: 'meals'}">My Meals</router-link>
        <router-link v-bind:to="{name: 'plans'}">My Meal Plans</router-link>
        <router-link v-bind:to="{name: 'grocery-list'}">My Grocery List</router-link>
        <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
      </div>
    </nav>
    <main>
      <div class="content">
        <router-view />
      </div>
    </main>
    <aside>
      <div class="content">
      </div>
    </aside>
    <footer>
      <div class="content">
        <p></p>
        
      </div>
    </footer>
   
  </div>
</template>


<style>
    body {
        margin: 0;
    }

    /* Add CSS Grid here  background colors: Header - #f69a00;  Nav - #f2ead2;  
    main - #f9f5f0;  aside - #f2ead2;  footer - #331312;
    font colors header, footer - #ffffff;  nav, main, aside - #74889a;*/

    .container{
        display: grid;

        grid-template-areas: 
            "header header header" 
            "nav content side"
            "footer footer footer";

        grid-template-columns: 200px 1fr 200px;
        grid-template-rows: 125px 1fr 85px;
        gap: 10px;

        height: 100vh;


    }
    /*handle responsive behavior */
    @media (max-width: 768px) {
        .container{
            grid-template-areas: 
                "header"
                "nav"
                "content"
                "side"
                "footer";
            grid-template-columns: 1fr;
            grid-template-rows:
                auto /*Header */
                75px /*Nav*/
                1fr /* content */
                75px /* Sidebar */
                auto; /* Footer */
            
        }
        header, footer, nav, main, aside {
            padding: 5px 0;
        }
        nav, aside {
        margin: 0;
        }

    }

    header {
    grid-area: header;
    background-color: #ffffff;
    text-align: center;
    border-bottom: 2px solid black;
    }

    
    header > img {
      height:100%;
    }

   #nav {
        grid-area: nav;        
        background-color: #f2ead2;
        display:flex;
        flex-direction: column;
        height: 100%;
    }
    #nav > a{
      padding: 10px;
    }


    main {
        grid-area: content;
        background-color: #95e0b7
        /* background-image: url("https://lh4.googleusercontent.com/Vrf5VUD-g3nJgSJc29YXKEJ7UomJhNi3qkXo6Cie6m-9Mq1GsluJJ6LC9_R2WCqd2_yAXN82MS9xhaTBV5n2OEQu485rPwFOiHdkh4F2ETeINj0Vwrl8e6loHOBy15d1n9L3CZ_8");
        background-repeat:repeat-y;
        background-size: 100%; */
    }

    aside {
        grid-area: side;        
        background-color: #f2ead2;
    }

    footer {
        grid-area: footer;
        background-color: #331312;
    }

    header, footer {
        color: #ffffff;
    }
    nav, main, aside {
        color:#00ff75;
    }

    h1, h2 {
  -webkit-text-stroke-width: 2.4px;
  -webkit-text-stroke-color: black;
    }

  h3, h4 {
    -webkit-text-stroke-width: 1.3px;
  -webkit-text-stroke-color: black;
  
}
    header, footer, nav, main, aside {
        font-family:'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        font-size: 24px;
        text-transform: uppercase;
    }

    div.content {
        text-align: center;
        padding-top: 20px;
    }
</style>