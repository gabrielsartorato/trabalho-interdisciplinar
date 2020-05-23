module.exports = {
    adjustName(userLocal){
        let user = userLocal.slice(0, 1).toUpperCase()

        user = user + userLocal.substring(1, userLocal.length).toLowerCase()

        return user
    }
}