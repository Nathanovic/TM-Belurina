<template>
    <div id="header">
        <h1>
            Tour Manager
        </h1>
        <nav>
            <ul>
                <li><nuxt-link to="/">Starting Page</nuxt-link></li>
                <li><nuxt-link to="/recipes">Recipes</nuxt-link></li>
                <li><nuxt-link to="/runners">Runners</nuxt-link></li>
            </ul>
        </nav>
    </div>
</template>

<style>
#header {	/*content div*/
	height: var(--value-headerHeight);
	
	background-color: var(--main-c3);
    color: var(--color-headerText);

    display: flex;
    flex-flow: column;
    justify-content: space-around;
    align-items: center;
}

#header ul{
    padding: 0;

    list-style: none;
    display: flex;
    flex-flow: row wrap;
    justify-content: center;
    align-items: center;
}

li {
    margin: 0px 10px;
}

a {
    text-decoration: none;
    color: white;
}
</style>
