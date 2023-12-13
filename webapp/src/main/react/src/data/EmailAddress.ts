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

    toString(): string {
        return `${this.username}@${this.domain}`;
    }
}
export default EmailAddress;