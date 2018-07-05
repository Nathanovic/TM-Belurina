<template>
    <div>
        <div id="wrapper">
            <Header/>
            <div id="body">
                <nuxt/>
            </div>
        </div>
        <div id="footer">
            <p>Copyright @ 2018</p>
        </div>
    </div>
</template>

<script>
import Header from "@/components/Header"
export default {
    components: {
        Header
    }
}
</script>


