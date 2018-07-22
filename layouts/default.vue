<template>
    <div>
        <div id="wrapper">
            <Header/>
            <div id="body">
                <nuxt/>
                <h2>running on {{ name[2] }}</h2>
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
    },
    data (process) {
        return { 
            name: [
                'client',
                'server',
                'pietje'
            ]
        }
    }
}
</script>



