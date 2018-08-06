import axios from 'axios';

function _getUrl(url) {
    return axios.get(url)
        .then(json => {
            return json.data;
        })
        .catch(e => {
            return { error: e }
        });
}

class RestService {
    constructor (siteurl) {
        this.baseUrl = siteurl;
    }

    teams () {
        return _getUrl(this.baseUrl + '/teams');
    }

    runners () {
        return _getUrl(this.baseUrl +'/runners');
    }

}

const rs = new RestService('http://185.95.31.64:4567');
export default rs;