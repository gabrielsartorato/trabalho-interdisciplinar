module.exports = {
    adjustName(userLocal){
        let user = userLocal.slice(0, 1).toUpperCase()

        user = user + userLocal.substring(1, userLocal.length).toLowerCase()

        return user
    },
    adjustBirthDateToForm(date) {
        const birthDate = date.slice(0,10).split("-")

        const birthDateAdjusted = birthDate[2] + '/' + birthDate[1] + '/' + birthDate[0]
        
        return birthDateAdjusted
    },
    adjustBirthDateToBackEnd(date) {

        date = date.split("/")

        let dateAdjusted = date[2] + '-' + date[1] + '-' + date[0]

        return dateAdjusted
    }
}