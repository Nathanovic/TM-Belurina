<template>
    <div id="runnerTable">
        <ul>
            <li class="runner" v-for="(runner, index) in runners" :key="index">
                <p>{{runner.firstName + " " + runner.lastName}}</p>
                <button v-if="canEdit" v-on:click="removeRunnerFunc(runner.runnerId)">remove runner</button>
            </li>
        </ul>
    </div>
</template>

<script>
export default {
    props: [ 'runners', 'canEdit', 'removeRunnerFunc' ]
}
</script>

<style scoped>
#container{
    margin: 1rem;
    padding: 1rem 1rem;
}

.runner{
    border: 1px solid rgb(53, 53, 53);
    padding: 20px;
    text-align: left;
    margin: -1px 0 0 0;
}

li{
    list-style: none;
}

</style>
