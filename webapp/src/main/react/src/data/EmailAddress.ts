class EmailAddress {
    private readonly username: string;
    private readonly domain: string;

    constructor(username: string, domain: string) {
        this.username = username;
        this.domain = domain;
    }

    getUsername(): string {
        return this.username;
    }

    getDomain(): string {
        return this.domain;
    }
}
export default EmailAddress;